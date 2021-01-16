package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgGotoInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.MgContinueCommand;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgContinueCommandInstructionCreationService extends MgService {
    public static void create(MgContinueCommand command, CommandContext commandContext, List<MgInstanceVariable> variables, List<MgInstruction> instructions){
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

        instructions.addLast(
            new MgGotoInstruction(begin)
        );
    }
}
