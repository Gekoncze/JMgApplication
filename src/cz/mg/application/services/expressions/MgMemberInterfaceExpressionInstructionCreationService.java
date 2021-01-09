package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.Connection;
import cz.mg.application.entities.runtime.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgMemberInterfaceCreateTaskInstruction;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;
import cz.mg.application.entities.statical.parts.expressions.MgMemberInterfaceExpression;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;

import java.util.Iterator;


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

        // todo - add variable compatibility checks

        List<Connection> input = new List<>();
        Iterator<MgInstanceVariable> procedureInputIterator = mgInterface.getInput().iterator();
        Iterator<MgInstanceVariable> inputOutputIterator = inputOutputs.iterator();
        input.addLast(new Connection(parentOutput, procedureInputIterator.next()));
        while(procedureInputIterator.hasNext() && inputOutputIterator.hasNext()){
            MgInstanceVariable procedureInput = procedureInputIterator.next();
            MgInstanceVariable inputOutput = inputOutputIterator.next();
            input.addLast(new Connection(inputOutput, procedureInput));
        }

        List<MgInstanceVariable> selfOutputs = new List<>();
        List<Connection> output = new List<>();
        for(MgInstanceVariable procedureOutput : mgInterface.getOutput()){
            MgInstanceVariable selfOutput = new MgExpressionVariable(procedureOutput.getDefinition());
            selfOutputs.addLast(selfOutput);
            output.addLast(new Connection(procedureOutput, selfOutput));
        }

        instructions.addLast(new MgMemberInterfaceCreateTaskInstruction(parentOutput, mgInterface, new Array<>(input)));
        instructions.addLast(new MgEnterTaskInstruction());
        instructions.addLast(new MgDestroyTaskInstruction(new Array<>(output)));

        variables.addCollectionLast(selfOutputs);
        return selfOutputs;
    }
}
