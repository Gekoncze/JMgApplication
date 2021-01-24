package cz.mg.application.services.expressions;

import cz.mg.application.entities.parts.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.parts.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.instructions.MgMemberInterfaceCreateTaskInstruction;
import cz.mg.application.entities.parts.MgInterface;
import cz.mg.application.entities.parts.expressions.MgExpression;
import cz.mg.application.entities.parts.expressions.MgMemberInterfaceExpression;
import cz.mg.application.entities.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.parts.variables.MgInterfaceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.validation.MgValidator;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.list.List;


public class MgMemberInterfaceExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgMemberInterfaceExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getParent() == null) throw new LogicalException(expression, "Missing parent expression.");
        if(expression.getInterface() == null) throw new LogicalException(expression, "Missing interface.");

        List<MgInstanceVariable> parentOutputs = MgExpressionInstructionCreationService.create(
            expression.getParent(), variables, instructions
        );

        if(parentOutputs.count() != 1){
            throw new LogicalException(expression, "Parent expression must return exactly one value.");
        }

        MgInstanceVariable parentOutput = parentOutputs.getFirst();

        List<MgInstanceVariable> inputOutputs = new List<>();
        for(MgExpression input : expression.getInput()){
            inputOutputs.addCollectionLast(
                MgExpressionInstructionCreationService.create(
                    input, variables, instructions
                )
            );
        }

        MgInterface mgInterface = expression.getInterface();

        if(mgInterface.getInput().count() < 1){
            throw new LogicalException(expression, "Interface must have at least one input in member call.");
        }

        if(mgInterface.getInput().count() != (inputOutputs.count() + 1)){
            throw new LogicalException(expression, "Interface input count mismatch.");
        }

        List<MgInstanceVariable> input = new List<>();
        input.addLast(parentOutput);
        for (MgInstanceVariable inputOutput : inputOutputs) {
            input.addLast(inputOutput);
        }

        List<MgInstanceVariable> selfOutputs = new List<>();
        List<MgInstanceVariable> output = new List<>();
        for(MgInterfaceVariable procedureOutput : mgInterface.getOutput()){
            MgInstanceVariable selfOutput = new MgExpressionVariable(procedureOutput.getDefinition());
            selfOutputs.addLast(selfOutput);
            output.addLast(selfOutput);
        }

        MgValidator.checkInputCompatibility(mgInterface, input);
        MgValidator.checkOutputCompatibility(mgInterface, output);

        instructions.addLast(new MgMemberInterfaceCreateTaskInstruction(parentOutput, mgInterface, new ReadonlyArray<>(input)));
        instructions.addLast(new MgEnterTaskInstruction());
        instructions.addLast(new MgDestroyTaskInstruction(new ReadonlyArray<>(output)));

        variables.addCollectionLast(selfOutputs);
        return selfOutputs;
    }
}
