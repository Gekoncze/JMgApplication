package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.*;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.collections.list.List;


public class MgCommandInstructionCreationService extends MgService {
    public static void create(MgCommand command, CommandContext commandContext, List<MgInstanceVariable> variables, List<MgInstruction> instructions){
        if(command instanceof MgBreakCommand){
            MgBreakCommandInstructionCreationService.create(
                (MgBreakCommand) command, commandContext, variables, instructions
            );
            return;
        }

        if(command instanceof MgContinueCommand){
            MgContinueCommandInstructionCreationService.create(
                (MgContinueCommand) command, commandContext, variables, instructions
            );
            return;
        }

        if(command instanceof MgExpressionCommand){
            //todo;
        }

        if(command instanceof MgReturnCommand){
            //todo;
        }

        if(command instanceof MgRollbackCommand){
            //todo;
        }

        if(command instanceof MgSwitchCommand){
            //todo;
        }

        if(command instanceof MgCaseCommand){
            //todo;
        }

        if(command instanceof MgWhileCommand){
            //todo;
        }

        throw new InternalException(command, "Could not create instructions. Unsupported command type " + command.getClass().getSimpleName() + ".");
    }
}
