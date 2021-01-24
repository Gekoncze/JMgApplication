package cz.mg.application.services.commands;

import cz.mg.application.entities.parts.instructions.MgBranchingInstruction;
import cz.mg.application.entities.parts.instructions.MgDummyInstruction;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.commands.MgWhileCommand;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.expressions.MgExpressionInstructionCreationService;
import cz.mg.collections.list.List;

import static cz.mg.application.services.expressions.MgExpressionInstructionCreationService.Pair;


public class MgWhileCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgWhileCommand command, CommandContext parent, List<MgInstanceVariable> variables){
        if(command.getExpression() == null) throw new LogicalException(command, "Missing expression.");

        MgDummyInstruction begin = new MgDummyInstruction();
        MgDummyInstruction end = new MgDummyInstruction();

        end.setNextInstruction(parent.getEnd());

        List<MgInstruction> blockInstructions = MgBlockCommandInstructionCreationService.create(
            command, new CommandContext(command, parent, begin, end), variables
        );

        MgBranchingInstruction branching = new MgBranchingInstruction(blockInstructions.getFirst(), end);

        Pair expresion = MgExpressionInstructionCreationService.createBoolean(
            command.getExpression(), variables, branching
        );

        branching.setCondition(expresion.getOutput());

        begin.setNextInstruction(expresion.getInstructions().getFirst());

        List<MgInstruction> instructions = new List<>();
        instructions.addLast(begin);
        instructions.addCollectionLast(expresion.getInstructions());
        instructions.addLast(branching);
        instructions.addCollectionLast(blockInstructions);
        instructions.addLast(end);
        return instructions;
    }
}
