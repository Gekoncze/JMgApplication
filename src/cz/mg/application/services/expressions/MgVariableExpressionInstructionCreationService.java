package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.expressions.MgVariableExpression;
import cz.mg.application.services.MgService;
import cz.mg.collections.list.List;


public class MgVariableExpressionInstructionCreationService extends MgService {
    public static List<MgVariable> create(
        MgVariableExpression expression,
        List<MgVariable> variables,
        List<MgInstruction> instructions
    ){
        // todo - add variable compatibility checks

        MgVariable selfOutput = expression.getVariable();

        variables.addLast(
            selfOutput
        );

        return new List<>(selfOutput);
    }
}
