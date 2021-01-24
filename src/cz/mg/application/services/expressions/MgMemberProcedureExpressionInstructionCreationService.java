package cz.mg.application.services.expressions;

import cz.mg.application.entities.parts.instructions.MgCreateTaskInstruction;
import cz.mg.application.entities.parts.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.parts.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.entities.parts.expressions.MgExpression;
import cz.mg.application.entities.parts.expressions.MgMemberProcedureExpression;
import cz.mg.application.entities.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.validation.MgValidator;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.list.List;


public class MgMemberProcedureExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgMemberProcedureExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getParent() == null) throw new LogicalException(expression, "Missing parent expression.");
        if(expression.getProcedure() == null) throw new LogicalException(expression, "Missing procedure.");

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

        MgProcedure procedure = expression.getProcedure();

        if(procedure.getInput().count() < 1){
            throw new LogicalException(expression, "Procedure must have at least one input in member call.");
        }

        if(procedure.getInput().count() != (inputOutputs.count() + 1)){
            throw new LogicalException(expression, "Procedure input count mismatch.");
        }

        List<MgInstanceVariable> input = new List<>();
        input.addLast(parentOutput);
        for (MgInstanceVariable inputOutput : inputOutputs) {
            input.addLast(inputOutput);
        }

        List<MgInstanceVariable> selfOutputs = new List<>();
        for(MgInstanceVariable procedureOutput : procedure.getOutput()){
            MgInstanceVariable selfOutput = new MgExpressionVariable(procedureOutput.getDefinition());
            selfOutputs.addLast(selfOutput);
        }

        MgValidator.checkInputCompatibility(procedure, input);
        MgValidator.checkOutputCompatibility(procedure, selfOutputs);

        instructions.addLast(new MgCreateTaskInstruction(procedure.getType(), new ReadonlyArray<>(input)));
        instructions.addLast(new MgEnterTaskInstruction());
        instructions.addLast(new MgDestroyTaskInstruction(new ReadonlyArray<>(selfOutputs)));

        variables.addCollectionLast(selfOutputs);
        return selfOutputs;
    }
}
