package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgCreateTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.components.definitions.MgBinaryOperator;
import cz.mg.application.entities.statical.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.expressions.MgBinaryOperatorExpression;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.list.List;

import java.util.Iterator;


public class MgBinaryOperatorExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgBinaryOperatorExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getOperator() == null) throw new LogicalException(expression, "Missing operator.");
        if(expression.getLeft() == null) throw new LogicalException(expression, "Missing left expression.");
        if(expression.getRight() == null) throw new LogicalException(expression, "Missing right expression.");

        MgBinaryOperator operator = expression.getOperator();

        List<MgInstanceVariable> leftOutputs = MgExpressionInstructionCreationService.create(
            expression.getLeft(), variables, instructions
        );
        List<MgInstanceVariable> rightOutputs = MgExpressionInstructionCreationService.create(
            expression.getRight(), variables, instructions
        );

        if(leftOutputs.count() != rightOutputs.count()){
            throw new LogicalException(expression, "Unbalanced operator expression. (" + leftOutputs.count() + " vs " + rightOutputs.count() + ")");
        }

        List<MgInstanceVariable> selfOutputs = new List<>();

        Iterator<MgInstanceVariable> leftOutputIterator = leftOutputs.iterator();
        Iterator<MgInstanceVariable> rightOutputIterator = rightOutputs.iterator();
        while(leftOutputIterator.hasNext() && rightOutputIterator.hasNext()){
            // todo - check for variable compatibility
            MgInstanceVariable leftSource = leftOutputIterator.next();
            MgInstanceVariable rightSource = rightOutputIterator.next();
            MgInstanceVariable selfOutput = new MgExpressionVariable(operator.getResult().getDefinition());
            selfOutputs.addLast(selfOutput);
            variables.addLast(selfOutput);
            instructions.addLast(new MgCreateTaskInstruction(
                operator.getType(),
                new ReadonlyArray<>(leftSource, rightSource)
            ));
            instructions.addLast(new MgEnterTaskInstruction());
            instructions.addLast(new MgDestroyTaskInstruction(
                new ReadonlyArray<>(selfOutput)
            ));
        }

        return selfOutputs;
    }
}
