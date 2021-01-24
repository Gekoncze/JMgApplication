package cz.mg.application.services.commands;

import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.commands.MgBlockCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgBlockCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgBlockCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        return create(command, command.getCommands(), commandContext, variables);
    }

    public static List<MgInstruction> create(MgEntity owner, List<MgStandaloneCommand> commands, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(commands.isEmpty()) throw new LogicalException(owner, "Missing commands.");
        List<MgInstruction> instructions = new List<>();
        MgInstruction next = commandContext.getEnd();
        for(MgStandaloneCommand childCommand : reverse(commands)){
            CommandContext childCommandContext = new CommandContext(commandContext, next);
            List<MgInstruction> childInstructions = MgCommandInstructionCreationService.create(childCommand, childCommandContext, variables);
            if(childInstructions.isEmpty()) throw new LogicalException(owner, "Missing instructions.");
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
