package cz.mg.application.services;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.application.entities.statical.parts.expressions.*;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.collections.list.List;


public class MgRuntimeExpressionService extends MgService {
    public static List<MgInstruction> create(MgCommand command, MgExpression expression){
        //todo;
//        expression.setRuntimeExpression(new MgRuntimeExpression(
//            expression,
//            variables
//        ));
//        return new List<>(
//            todo
//        );
        return null;
    }

    private static void create(MgCommand command, MgExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        if(expression instanceof MgAssignmentExpression){
            create(command, (MgAssignmentExpression)expression, variables, instructions);
            return;
        }

        if(expression instanceof MgBinaryOperatorExpression){
            create(command, (MgBinaryOperatorExpression)expression, variables, instructions);
            return;
        }

        if(expression instanceof MgGroupExpression){
            create(command, (MgGroupExpression)expression, variables, instructions);
            return;
        }

        if(expression instanceof MgLunaryOperatorExpression){
            create(command, (MgLunaryOperatorExpression) expression, variables, instructions);
            return;
        }

        if(expression instanceof MgMemberExpression){
            create(command, (MgMemberExpression) expression, variables, instructions);
            return;
        }

        if(expression instanceof MgProcedureExpression){
            create(command, (MgProcedureExpression) expression, variables, instructions);
            return;
        }

        if(expression instanceof MgRunaryOperatorExpression){
            create(command, (MgRunaryOperatorExpression) expression, variables, instructions);
            return;
        }

        if(expression instanceof MgValueExpression){
            create(command, (MgValueExpression) expression, variables, instructions);
            return;
        }

        throw new InternalException(expression, "Could not create instructions. Unsupported expression type " + expression.getClass().getSimpleName() + ".");
    }

    private static void create(MgCommand command, MgAssignmentExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgCommand command, MgBinaryOperatorExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgCommand command, MgGroupExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgCommand command, MgLunaryOperatorExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgCommand command, MgMemberExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgCommand command, MgProcedureExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgCommand command, MgRunaryOperatorExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }

    private static void create(MgCommand command, MgValueExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
    }
}
