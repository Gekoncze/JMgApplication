package cz.mg.application.services.commands;

import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.commands.*;
import cz.mg.application.entities.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.collections.list.List;


public class MgCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgStandaloneCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
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
            return MgReturnCommandInstructionCreationService.create(
                (MgReturnCommand) command, commandContext, variables
            );
        }

        if(command instanceof MgRollbackCommand){
            return MgRollbackCommandInstructionCreationService.create(
                (MgRollbackCommand) command, commandContext, variables
            );
        }

        if(command instanceof MgSwitchCommand){
            return MgSwitchCommandInstructionCreationService.create(
                (MgSwitchCommand) command, commandContext, variables
            );
        }

        if(command instanceof MgWhileCommand){
            return MgWhileCommandInstructionCreationService.create(
                (MgWhileCommand) command, commandContext, variables
            );
        }

        throw new InternalException((MgCommand)command, "Could not create instructions. Unsupported command type " + command.getClass().getSimpleName() + ".");
    }
}
