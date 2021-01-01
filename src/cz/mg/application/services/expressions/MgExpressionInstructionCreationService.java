package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgLinearInstruction;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.expressions.*;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.collections.list.List;


public class MgExpressionInstructionCreationService extends MgService {
    public static void connectInstructions(List<MgInstruction> instructions, MgInstruction nextInstruction){
        for(MgInstruction instruction : reverse(instructions)){
            MgLinearInstruction linearInstruction = (MgLinearInstruction) instruction;
            linearInstruction.setNextInstruction(nextInstruction);
            nextInstruction = instruction;
        }
    }

    private static List<MgInstruction> reverse(List<MgInstruction> instructions){
        List<MgInstruction> reversed = new List<>();
        for(MgInstruction instruction : instructions) reversed.addFirst(instruction);
        return reversed;
    }

    public static List<MgVariable> create(MgExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        if(expression instanceof MgAssignmentExpression){
            return MgAssignmentExpressionInstructionCreationService.create(
                (MgAssignmentExpression)expression, variables, instructions
            );
        }

        if(expression instanceof MgBinaryOperatorExpression){
            return MgBinaryOperatorExpressionInstructionCreationService.create(
                (MgBinaryOperatorExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgGroupExpression){
            return MgGroupExpressionInstructionCreationService.create(
                (MgGroupExpression)expression, variables, instructions
            );
        }

        if(expression instanceof MgLunaryOperatorExpression){
            return MgLunaryOperatorExpressionInstructionCreationService.create(
                (MgLunaryOperatorExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgMemberExpression){
            return create((MgMemberExpression) expression, variables, instructions);
        }

        if(expression instanceof MgProcedureExpression){
            return create((MgProcedureExpression) expression, variables, instructions);
        }

        if(expression instanceof MgRunaryOperatorExpression){
            return MgRunaryOperatorExpressionInstructionCreationService.create(
                (MgRunaryOperatorExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgValueExpression){
            return create((MgValueExpression) expression, variables, instructions);
        }

        if(expression instanceof MgVariableExpression){
            return create((MgVariableExpression) expression, variables, instructions);
        }

        throw new InternalException(expression, "Could not create instructions. Unsupported expression type " + expression.getClass().getSimpleName() + ".");
    }

    private static List<MgVariable> create(MgGroupExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
        return null;
    }

    private static List<MgVariable> create(MgMemberExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
        return null;
    }

    private static List<MgVariable> create(MgProcedureExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
        return null;
    }

    private static List<MgVariable> create(MgValueExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
        return null;
    }

    private static List<MgVariable> create(MgVariableExpression expression, List<MgVariable> variables, List<MgInstruction> instructions){
        //todo;
        return null;
    }
}
