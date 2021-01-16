package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.MgBlockCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgBlockCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgBlockCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(command.getCommands().isEmpty()) throw new LogicalException(command, "Missing child blocks.");
        List<MgInstruction> instructions = new List<>();
        MgInstruction next = commandContext.getEnd();
        for(MgStandaloneCommand childCommand : reverse(command.getCommands())){
            CommandContext childCommandContext = new CommandContext(commandContext, next);
            List<MgInstruction> childInstructions = MgCommandInstructionCreationService.create(childCommand, childCommandContext, variables);
            if(childInstructions.isEmpty()) throw new LogicalException(command, "Missing child block instructions.");
            instructions.addCollectionFirst(childInstructions);
            next = childInstructions.getFirst();
        }
        return instructions;
    }

    public static List<MgStandaloneCommand> reverse(List<MgStandaloneCommand> commands){
        List<MgStandaloneCommand> reversed = new List<>();
        for(MgStandaloneCommand command : commands){
            reversed.addFirst(command);
        }
        return reversed;
    }
}
