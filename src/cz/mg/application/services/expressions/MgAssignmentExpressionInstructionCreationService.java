package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgSetLocalToLocalInstruction;
import cz.mg.application.entities.runtime.instructions.MgSetLocalToMemberInstruction;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.expressions.*;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;

import java.util.Iterator;


public class MgAssignmentExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgAssignmentExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getLeft() == null) throw new LogicalException(expression, "Missing left expression.");
        if(expression.getRight() == null) throw new LogicalException(expression, "Missing right expression.");

        List<MgInstanceVariable> leftOutputs = MgExpressionInstructionCreationService.create(
            expression.getLeft(), variables, instructions
        );
        List<MgInstanceVariable> rightOutputs = MgExpressionInstructionCreationService.create(
            expression.getRight(), variables, instructions
        );

        if(leftOutputs.count() != rightOutputs.count()){
            throw new LogicalException(expression, "Unbalanced assignment expression. (" + leftOutputs.count() + " vs " + rightOutputs.count() + ")");
        }

        List<MgExpression> leftExpressions = unwrap(expression.getLeft());

        if(leftOutputs.count() != leftExpressions.count()){
            throw new LogicalException(expression, "Cannot assign values to left expression.");
        }

        Iterator<MgInstanceVariable> leftOutputIterator = leftOutputs.iterator();
        Iterator<MgInstanceVariable> rightOutputIterator = rightOutputs.iterator();
        for(MgExpression leftExpression : leftExpressions){
            MgInstanceVariable leftOutput = leftOutputIterator.next();
            MgInstanceVariable rightOutput = rightOutputIterator.next();
            if(leftExpression instanceof MgLocalVariableExpression){
                MgInstanceVariable variable = ((MgLocalVariableExpression) leftExpression).getVariable();
                instructions.addLast(new MgSetLocalToLocalInstruction(rightOutput, variable));
                // todo - check for variable compatibility
            } else if(leftExpression instanceof MgMemberVariableExpression){
                MgMemberVariableExpression memberExpression = (MgMemberVariableExpression) leftExpression;
                MgInstanceVariable variable = memberExpression.getVariable();
                instructions.addLast(new MgSetLocalToMemberInstruction(rightOutput, leftOutput, variable));
                // todo - check for variable compatibility
            } else {
                throw new LogicalException(expression, "Illegal assignment expression.");
            }
        }
        return new List<>();
    }

    private static List<MgExpression> unwrap(MgExpression expression){
        List<MgExpression> expressions = new List<>();
        unwrap(expression, expressions);
        return expressions;
    }

    private static void unwrap(MgExpression expression, List<MgExpression> expressions){
        if(expression instanceof MgGroupExpression){
            MgGroupExpression group = (MgGroupExpression) expression;
            for(MgExpression groupExpression : group.getExpressions()){
                unwrap(groupExpression, expressions);
            }
        } else {
            expressions.addLast(expression);
        }
    }
}
