package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgCreateTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;
import cz.mg.application.entities.statical.parts.expressions.MgProcedureExpression;
import cz.mg.application.entities.statical.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.list.List;


public class MgProcedureExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgProcedureExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getProcedure() == null) throw new LogicalException(expression, "Missing procedure.");

        List<MgInstanceVariable> inputOutputs = new List<>();
        for(MgExpression input : expression.getInput()){
            inputOutputs.addCollectionLast(
                MgExpressionInstructionCreationService.create(
                    input, variables, instructions
                )
            );
        }

        MgProcedure procedure = expression.getProcedure();

        if(procedure.getInput().count() != inputOutputs.count()){
            throw new LogicalException(expression, "Procedure input count mismatch.");
        }

        // todo - add variable compatibility checks

        List<MgInstanceVariable> selfOutputs = new List<>();
        for(MgInstanceVariable procedureOutput : procedure.getOutput()){
            MgInstanceVariable selfOutput = new MgExpressionVariable(procedureOutput.getDefinition());
            selfOutputs.addLast(selfOutput);
        }

        instructions.addLast(new MgCreateTaskInstruction(procedure.getType(), new ReadonlyArray<>(inputOutputs)));
        instructions.addLast(new MgEnterTaskInstruction());
        instructions.addLast(new MgDestroyTaskInstruction(new ReadonlyArray<>(selfOutputs)));

        variables.addCollectionLast(selfOutputs);
        return selfOutputs;
    }
}
