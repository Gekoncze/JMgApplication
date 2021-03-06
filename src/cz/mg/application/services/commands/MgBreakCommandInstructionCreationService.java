package cz.mg.application.services.commands;

import cz.mg.application.entities.parts.instructions.MgGotoInstruction;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.commands.MgBreakCommand;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgBreakCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgBreakCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(command.getTarget() == null) throw new LogicalException(command, "Missing target command.");

        MgInstruction end = null;

        while(commandContext != null){
            if(commandContext.getCommand() == command.getTarget()){
                end = commandContext.getEnd();
                break;
            }
            commandContext = commandContext.getParent();
        }

        if(end == null){
            throw new LogicalException(command, "Could not find target command.");
        }

        return new List<>(new MgGotoInstruction(end));
    }
}
