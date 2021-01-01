package cz.mg.application.services;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.expressions.*;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.application.services.expressions.MgRuntimeAssignmentExpressionInstructionCreationService;
import cz.mg.collections.list.List;


public class MgExpressionInstructionCreationService extends MgService {
    public static void create(MgExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        if(expression instanceof MgAssignmentExpression){
            MgRuntimeAssignmentExpressionInstructionCreationService.create(
                (MgAssignmentExpression)expression, variables, instructions
            );
            return;
        }

        if(expression instanceof MgBinaryOperatorExpression){
            create((MgBinaryOperatorExpression)expression, variables, instructions);
            return;
        }

        if(expression instanceof MgGroupExpression){
            create((MgGroupExpression)expression, variables, instructions);
            return;
        }

        if(expression instanceof MgLunaryOperatorExpression){
            create((MgLunaryOperatorExpression) expression, variables, instructions);
            return;
        }

        if(expression instanceof MgMemberExpression){
            create((MgMemberExpression) expression, variables, instructions);
            return;
        }

        if(expression instanceof MgProcedureExpression){
            create((MgProcedureExpression) expression, variables, instructions);
            return;
        }

        if(expression instanceof MgRunaryOperatorExpression){
            create((MgRunaryOperatorExpression) expression, variables, instructions);
            return;
        }

        if(expression instanceof MgValueExpression){
            create((MgValueExpression) expression, variables, instructions);
            return;
        }

        if(expression instanceof MgVariableExpression){
            create((MgVariableExpression) expression, variables, instructions);
            return;
        }

        throw new InternalException(expression, "Could not create instructions. Unsupported expression type " + expression.getClass().getSimpleName() + ".");
    }

    private static void create(MgBinaryOperatorExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgGroupExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgLunaryOperatorExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgMemberExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgProcedureExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgRunaryOperatorExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgValueExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgVariableExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }
}
