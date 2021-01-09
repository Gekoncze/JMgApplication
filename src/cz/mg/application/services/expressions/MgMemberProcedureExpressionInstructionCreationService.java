package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.Connection;
import cz.mg.application.entities.runtime.instructions.*;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;
import cz.mg.application.entities.statical.parts.expressions.MgMemberProcedureExpression;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;

import java.util.Iterator;


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

        // todo - add variable compatibility checks

        List<Connection> input = new List<>();
        Iterator<MgInstanceVariable> procedureInputIterator = procedure.getInput().iterator();
        Iterator<MgInstanceVariable> inputOutputIterator = inputOutputs.iterator();
        input.addLast(new Connection(parentOutput, procedureInputIterator.next()));
        while(procedureInputIterator.hasNext() && inputOutputIterator.hasNext()){
            MgInstanceVariable procedureInput = procedureInputIterator.next();
            MgInstanceVariable inputOutput = inputOutputIterator.next();
            input.addLast(new Connection(inputOutput, procedureInput));
        }

        List<MgInstanceVariable> selfOutputs = new List<>();
        List<Connection> output = new List<>();
        for(MgInstanceVariable procedureOutput : procedure.getOutput()){
            MgInstanceVariable selfOutput = new MgExpressionVariable(procedureOutput.getDefinition());
            selfOutputs.addLast(selfOutput);
            output.addLast(new Connection(procedureOutput, selfOutput));
        }

        instructions.addLast(new MgCreateTaskInstruction(procedure.getType(), new Array<>(input)));
        instructions.addLast(new MgEnterTaskInstruction());
        instructions.addLast(new MgDestroyTaskInstruction(new Array<>(output)));

        variables.addCollectionLast(selfOutputs);
        return selfOutputs;
    }
}
