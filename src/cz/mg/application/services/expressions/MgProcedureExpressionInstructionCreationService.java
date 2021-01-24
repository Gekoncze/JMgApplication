package cz.mg.application.services.expressions;

import cz.mg.application.entities.parts.instructions.MgCreateTaskInstruction;
import cz.mg.application.entities.parts.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.parts.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.entities.parts.expressions.MgExpression;
import cz.mg.application.entities.parts.expressions.MgProcedureExpression;
import cz.mg.application.entities.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.validation.MgValidator;
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

        List<MgInstanceVariable> selfOutputs = new List<>();
        for(MgInstanceVariable procedureOutput : procedure.getOutput()){
            MgInstanceVariable selfOutput = new MgExpressionVariable(procedureOutput.getDefinition());
            selfOutputs.addLast(selfOutput);
        }

        MgValidator.checkInputCompatibility(procedure, inputOutputs);
        MgValidator.checkOutputCompatibility(procedure, selfOutputs);

        instructions.addLast(new MgCreateTaskInstruction(procedure.getType(), new ReadonlyArray<>(inputOutputs)));
        instructions.addLast(new MgEnterTaskInstruction());
        instructions.addLast(new MgDestroyTaskInstruction(new ReadonlyArray<>(selfOutputs)));

        variables.addCollectionLast(selfOutputs);
        return selfOutputs;
    }
}
