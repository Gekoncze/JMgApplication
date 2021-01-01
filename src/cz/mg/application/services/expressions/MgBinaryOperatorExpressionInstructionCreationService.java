package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.Connection;
import cz.mg.application.entities.runtime.instructions.MgCreateTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.components.definitions.MgBinaryOperator;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.expressions.MgBinaryOperatorExpression;
import cz.mg.application.services.MgExpressionInstructionCreationService;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;

import java.util.Iterator;


public class MgBinaryOperatorExpressionInstructionCreationService extends MgService {
    public static List<MgVariable> create(
        MgBinaryOperatorExpression expression,
        List<MgVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getOperator() == null) throw new LogicalException(expression, "Missing operator.");
        if(expression.getLeft() == null) throw new LogicalException(expression, "Missing left expression.");
        if(expression.getRight() == null) throw new LogicalException(expression, "Missing right expression.");

        MgBinaryOperator operator = expression.getOperator();

        List<MgVariable> leftOutputs = MgExpressionInstructionCreationService.create(
            expression.getLeft(), variables, instructions
        );
        List<MgVariable> rightOutputs = MgExpressionInstructionCreationService.create(
            expression.getRight(), variables, instructions
        );

        if(leftOutputs.count() != rightOutputs.count()){
            throw new LogicalException(expression, "Unbalanced operator expression. (" + leftOutputs.count() + " vs " + rightOutputs.count() + ")");
        }

        List<MgVariable> selfOutputs = new List<>();

        Iterator<MgVariable> leftOutputIterator = leftOutputs.iterator();
        Iterator<MgVariable> rightOutputIterator = rightOutputs.iterator();
        while(leftOutputIterator.hasNext() && rightOutputIterator.hasNext()){
            // todo - check for variable compatibility
            MgVariable leftSource = leftOutputIterator.next();
            MgVariable rightSource = rightOutputIterator.next();
            MgVariable selfOutput = new MgVariable();
            selfOutput.setDefinition(operator.getResult().getDefinition());
            selfOutputs.addLast(selfOutput);
            variables.addLast(selfOutput);
            instructions.addLast(new MgCreateTaskInstruction(
                operator.getType(),
                new Array<>(
                    new Connection(leftSource, operator.getLeft()),
                    new Connection(rightSource, operator.getRight())
                )
            ));
            instructions.addLast(new MgEnterTaskInstruction());
            instructions.addLast(new MgDestroyTaskInstruction(
                operator.getType(),
                new Array<>(
                    new Connection(operator.getResult(), selfOutput)
                )
            ));
        }

        return selfOutputs;
    }
}
