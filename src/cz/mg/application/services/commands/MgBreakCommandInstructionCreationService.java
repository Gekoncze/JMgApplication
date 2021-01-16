package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgGotoInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.MgBreakCommand;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgBreakCommandInstructionCreationService extends MgService {
    public static void create(MgBreakCommand command, CommandContext commandContext, List<MgInstanceVariable> variables, List<MgInstruction> instructions){
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

        instructions.addLast(
            new MgGotoInstruction(end)
        );
    }
}
