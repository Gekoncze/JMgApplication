package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.*;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.collections.list.List;


public class MgCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(command instanceof MgBreakCommand){
            return MgBreakCommandInstructionCreationService.create(
                (MgBreakCommand) command, commandContext, variables
            );
        }

        if(command instanceof MgContinueCommand){
            return MgContinueCommandInstructionCreationService.create(
                (MgContinueCommand) command, commandContext, variables
            );
        }

        if(command instanceof MgExpressionCommand){
            return MgExpressionCommandInstructionCreationService.create(
                (MgExpressionCommand) command, commandContext, variables
            );
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
