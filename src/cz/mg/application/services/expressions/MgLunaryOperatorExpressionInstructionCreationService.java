package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgCreateTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.components.definitions.MgLunaryOperator;
import cz.mg.application.entities.statical.parts.expressions.MgLunaryOperatorExpression;
import cz.mg.application.entities.statical.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.list.List;


public class MgLunaryOperatorExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgLunaryOperatorExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getOperator() == null) throw new LogicalException(expression, "Missing operator.");
        if(expression.getRight() == null) throw new LogicalException(expression, "Missing right expression.");

        MgLunaryOperator operator = expression.getOperator();

        List<MgInstanceVariable> rightOutputs = MgExpressionInstructionCreationService.create(
            expression.getRight(), variables, instructions
        );

        List<MgInstanceVariable> selfOutputs = new List<>();

        for (MgInstanceVariable rightSource : rightOutputs) {
            MgInstanceVariable selfOutput = new MgExpressionVariable(operator.getResult().getDefinition());
            selfOutputs.addLast(selfOutput);
            variables.addLast(selfOutput);
            instructions.addLast(new MgCreateTaskInstruction(
                operator.getType(),
                new ReadonlyArray<>(rightSource)
            ));
            instructions.addLast(new MgEnterTaskInstruction());
            instructions.addLast(new MgDestroyTaskInstruction(
                new ReadonlyArray<>(selfOutput)
            ));
        }

        return selfOutputs;
    }
}
