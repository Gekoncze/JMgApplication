package cz.mg.application.services.commands;

import cz.mg.application.entities.parts.instructions.MgGotoInstruction;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.commands.MgContinueCommand;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgContinueCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgContinueCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(command.getTarget() == null) throw new LogicalException(command, "Missing target command.");

        MgInstruction begin = null;

        while(commandContext != null){
            if(commandContext.getCommand() == command.getTarget()){
                begin = commandContext.getBegin();
                break;
            }
            commandContext = commandContext.getParent();
        }

        if(begin == null){
            throw new LogicalException(command, "Could not find target command.");
        }

        return new List<>(new MgGotoInstruction(begin));
    }
}
