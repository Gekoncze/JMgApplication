package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgMemberVariableInstruction;
import cz.mg.application.entities.statical.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.expressions.MgMemberVariableExpression;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgMemberVariableExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgMemberVariableExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getParent() == null) throw new LogicalException(expression, "Missing parent expression.");

        List<MgInstanceVariable> parentOutputs = MgExpressionInstructionCreationService.create(
            expression.getParent(), variables, instructions
        );

        if(parentOutputs.count() != 1){
            throw new LogicalException(expression, "Parent expression must return exactly one value.");
        }

        MgInstanceVariable parentOutput = parentOutputs.getFirst();

        // todo - add variable compatibility checks

        MgInstanceVariable selfOutput = new MgExpressionVariable(expression.getVariable().getDefinition());

        instructions.addLast(
            new MgMemberVariableInstruction(
                parentOutput,
                expression.getVariable(),
                selfOutput
            )
        );

        variables.addLast(
            selfOutput
        );

        return new List<>(selfOutput);
    }
}
