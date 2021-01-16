package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgDummyInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.MgCaseCommand;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.collections.list.List;


public class MgCaseCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgCaseCommand command, CommandContext commandContext, List<MgInstanceVariable> variables) {
        MgDummyInstruction begin = new MgDummyInstruction();
        MgDummyInstruction end = new MgDummyInstruction();

        end.setNextInstruction(commandContext.getEnd());

        List<MgInstruction> blockInstructions = MgBlockCommandInstructionCreationService.create(
            command, new CommandContext(command, commandContext, begin, end), variables
        );

        begin.setNextInstruction(blockInstructions.getFirst());

        List<MgInstruction> instructions = new List<>();
        instructions.addLast(begin);
        instructions.addCollectionLast(blockInstructions);
        instructions.addLast(end);
        return instructions;
    }
}
