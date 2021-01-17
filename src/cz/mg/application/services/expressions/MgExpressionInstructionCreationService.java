package cz.mg.application.services.expressions;

import cz.mg.application.entities.buildin.atoms.bool8.MgBool8;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgLinearInstruction;
import cz.mg.application.entities.statical.parts.expressions.*;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.variables.MgVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgExpressionInstructionCreationService extends MgService {
    public static Pair createVoid(MgExpression expression, List<MgInstanceVariable> variables, MgInstruction nextInstruction){
        Pair pair = new Pair();
        List<MgInstanceVariable> output = create(expression, variables, pair.getInstructions());
        if(pair.getInstructions().isEmpty()) throw new LogicalException(expression, "Missing expression instructions.");
        if(!output.isEmpty()) throw new LogicalException(expression, "Expected no output.");
        connect(pair.getInstructions(), nextInstruction);
        return pair;
    }

    public static Pair createBoolean(MgExpression expression, List<MgInstanceVariable> variables, MgInstruction nextInstruction){
        Pair pair = new Pair();
        List<MgInstanceVariable> output = create(expression, variables, pair.getInstructions());
        if(pair.getInstructions().isEmpty()) throw new LogicalException(expression, "Missing expression instructions.");
        if(output.count() != 1) throw new LogicalException(expression, "Expected single output.");
        if(output.getLast().getDefinition() != MgBool8.getInstance()) throw new LogicalException(expression, "Expected boolean output.");
        connect(pair.getInstructions(), nextInstruction);
        pair.setOutput(output.getLast());
        return pair;
    }

    public static Pair createException(MgExpression expression, List<MgInstanceVariable> variables, MgInstruction nextInstruction){
        Pair pair = new Pair();
        List<MgInstanceVariable> output = create(expression, variables, pair.getInstructions());
        if(pair.getInstructions().isEmpty()) throw new LogicalException(expression, "Missing expression instructions.");
        if(output.count() != 1) throw new LogicalException(expression, "Expected single output.");
        //TODO - if(output.getLast().getDefinition() != MgException.getInstance()) throw new LogicalException(expression, "Expected exception output.");
        connect(pair.getInstructions(), nextInstruction);
        pair.setOutput(output.getLast());
        return pair;
    }

    private static void connect(List<MgInstruction> instructions, MgInstruction nextInstruction){
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

    public static List<MgInstanceVariable> create(MgExpression expression, List<MgInstanceVariable> variables, List<MgInstruction> instructions){
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

        if(expression instanceof MgMemberInterfaceExpression){
            return MgMemberInterfaceExpressionInstructionCreationService.create(
                (MgMemberInterfaceExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgMemberProcedureExpression){
            return MgMemberProcedureExpressionInstructionCreationService.create(
                (MgMemberProcedureExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgMemberVariableExpression){
            return MgMemberVariableExpressionInstructionCreationService.create(
                (MgMemberVariableExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgProcedureExpression){
            return MgProcedureExpressionInstructionCreationService.create(
                (MgProcedureExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgRunaryOperatorExpression){
            return MgRunaryOperatorExpressionInstructionCreationService.create(
                (MgRunaryOperatorExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgValueExpression){
            return MgValueExpressionInstructionCreationService.create(
                (MgValueExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgLocalVariableExpression){
            return MgLocalVariableExpressionInstructionCreationService.create(
                (MgLocalVariableExpression) expression, variables, instructions
            );
        }

        if(expression instanceof MgBuildinExpression){
            return MgBuildinExpressionInstructionCreationService.create(
                (MgBuildinExpression) expression, variables, instructions
            );
        }

        throw new InternalException(expression, "Could not create instructions. Unsupported expression type " + expression.getClass().getSimpleName() + ".");
    }

    public static class Pair {
        private final List<MgInstruction> instructions = new List<>();
        private MgVariable output;

        public Pair() {
        }

        public List<MgInstruction> getInstructions() {
            return instructions;
        }

        public MgVariable getOutput() {
            return output;
        }

        public void setOutput(MgVariable output) {
            this.output = output;
        }
    }
}
