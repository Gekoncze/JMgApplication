package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.expressions.MgLocalVariableExpression;
import cz.mg.application.services.MgService;
import cz.mg.collections.list.List;


public class MgVariableExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgLocalVariableExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        // todo - add variable compatibility checks

        MgInstanceVariable selfOutput = expression.getVariable();

        variables.addLast(
            selfOutput
        );

        return new List<>(selfOutput);
    }
}
