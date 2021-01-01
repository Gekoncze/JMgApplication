package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.expressions.MgAssignmentExpression;
import cz.mg.application.services.MgService;
import cz.mg.collections.list.List;


public class MgRuntimeAssignmentExpressionInstructionCreationService extends MgService {
    public static void create(MgAssignmentExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
//        check(expression, expression.getLeft());
//        check(expression, expression.getRight());
//        MgExpressionInstructionCreationService.create(expression.getLeft(), variables, instructions);
//        MgExpressionInstructionCreationService.create(expression.getRight(), variables, instructions);
//        List<MgExpression> leftExpressions = unwrap(expression.getLeft());
//        int leftCount = leftExpressions.count();
//        int rightCount = expression.getRight().getRuntimeExpression().getVariables().count();
//        if(leftCount != rightCount) throw new LogicalException(expression, "Unbalanced assignment expression. (" + leftCount + " vs " + rightCount + ")");
//        Iterator<MgVariable> rightVariables = expression.getRight().getRuntimeExpression().getVariables().iterator();
//        for(MgExpression leftExpression : leftExpressions){
//            MgVariable rightVariable = rightVariables.next();
//            if(leftExpression instanceof MgVariableExpression){
//                MgVariable leftVariable = ((MgVariableExpression) leftExpression).getVariable();
//                instructions.addLast(new MgSetLocalToLocalInstruction(command, rightVariable, leftVariable));
//            } else if(leftExpression instanceof MgMemberExpression){
//                MgMemberExpression memberExpression = (MgMemberExpression) leftExpression;
//                if(memberExpression.getChild() instanceof MgVariableExpression){
//                    // todo - create member assignment instructions
//                } else {
//                    throw new LogicalException(expression, "Illegal assignment expression.");
//                }
//            } else {
//                throw new LogicalException(expression, "Illegal assignment expression.");
//            }
//        }
    }

//    private static List<MgExpression> unwrap(MgExpression expression){
//        List<MgExpression> expressions = new List<>();
//        unwrap(expression, expressions);
//        return expressions;
//    }
//
//    private static void unwrap(MgExpression expression, List<MgExpression> expressions){
//        if(expression instanceof MgGroupExpression){
//            MgGroupExpression group = (MgGroupExpression) expression;
//            for(MgExpression groupExpression : group.getExpressions()){
//                unwrap(groupExpression, expressions);
//            }
//        } else {
//            expressions.addLast(expression);
//        }
//    }
}
