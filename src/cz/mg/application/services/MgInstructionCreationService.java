package cz.mg.application.services;

import cz.mg.application.entities.runtime.MgRuntimeCommand;
import cz.mg.application.entities.runtime.instructions.MgGotoInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.dummy.MgBeginDummyInstruction;
import cz.mg.application.entities.runtime.instructions.dummy.MgEndDummyInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.commands.*;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgAnyCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;

import static cz.mg.application.services.MgInstructionOptimizationService.optimize;
import static cz.mg.application.services.MgInstructionValidationService.validate;


public class MgInstructionCreationService extends MgInstructionService {
    public static void create(MgProcedure procedure){
        check(procedure);
        createSequence(procedure.getCommands(), null, null);
        optimize(procedure);
        validate(procedure);
    }

    private static void createSequence(List<MgStandaloneCommand> commands, MgStandaloneCommand nextCommand, MgCommand parent){
        for(MgStandaloneCommand command : reverse(commands)){
            create(command, nextCommand, parent);
            nextCommand = command;
        }
    }

    private static List<MgStandaloneCommand> reverse(List<MgStandaloneCommand> commands){
        List<MgStandaloneCommand> reversed = new List<>();
        for(MgStandaloneCommand command : commands){
            reversed.addFirst(command);
        }
        return reversed;
    }

    private static void create(MgAnyCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        if(command instanceof MgBreakCommand){
            create((MgBreakCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgCaseCommand){
            create((MgCaseCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgCatchCommand){
            create((MgCatchCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgCheckpointCommand){
            create((MgCheckpointCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgContinueCommand){
            create((MgContinueCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgExpressionCommand){
            create((MgExpressionCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgReturnCommand){
            create((MgReturnCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgRollbackCommand){
            create((MgRollbackCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgSwitchCommand){
            create((MgSwitchCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgTryCommand){
            create((MgTryCommand) command, nextCommand, parent);
            return;
        }

        if(command instanceof MgWhileCommand){
            create((MgWhileCommand) command, nextCommand, parent);
            return;
        }

        throw new InternalException((MgCommand)command, "Could not create instructions. Unsupported command type " + command.getClass().getSimpleName() + ".");
    }

    private static void create(MgBreakCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        MgCommand target = (MgCommand) command.getTarget();
        command.setRuntimeCommand(new MgRuntimeCommand(
            command,
            parent,
            new Array<>(
                new MgGotoInstruction(command, target.getRuntimeCommand().getInstructions().getLast())
            ),
            new Array<>()
        ));
    }

    private static void create(MgCaseCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        createDummyInstructions(command, parent);
        createSequence(command.getCommands(), nextCommand, command);
        connectDummyInstructions(command, command.getCommands(), nextCommand);
    }

    private static void create(MgCatchCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        createDummyInstructions(command, parent);
        createSequence(command.getCommands(), nextCommand, command);
        connectDummyInstructions(command, command.getCommands(), nextCommand);
    }

    private static void create(MgCheckpointCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        //todo;
    }

    private static void create(MgContinueCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        MgCommand target = (MgCommand) command.getTarget();
        command.setRuntimeCommand(new MgRuntimeCommand(
            command,
            parent,
            new Array<>(
                new MgGotoInstruction(command, target.getRuntimeCommand().getInstructions().getFirst())
            ),
            new Array<>()
        ));
    }

    private static void create(MgExpressionCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        //todo;
    }

    private static void create(MgReturnCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        //todo;
    }

    private static void create(MgRollbackCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        //todo;
    }

    private static void create(MgSwitchCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        //todo;
    }

    private static void create(MgTryCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        //todo;
    }

    private static void create(MgWhileCommand command, MgStandaloneCommand nextCommand, MgCommand parent){
        //todo;
    }

    private static void createDummyInstructions(MgBlockCommand command, MgCommand parent){
        command.setRuntimeCommand(new MgRuntimeCommand(
            command,
            parent,
            new Array<>(
                new MgBeginDummyInstruction(command),
                new MgEndDummyInstruction(command)
            ),
            new Array<>(command.getCommands())
        ));
    }

    private static void connectDummyInstructions(MgBlockCommand command, List<MgStandaloneCommand> commands, MgStandaloneCommand nextCommand){
        MgBeginDummyInstruction begin = (MgBeginDummyInstruction) command.getRuntimeCommand().getInstructions().getFirst();
        MgEndDummyInstruction end = (MgEndDummyInstruction) command.getRuntimeCommand().getInstructions().getLast();

        if(commands.isEmpty()){
            begin.setNextInstruction(end);
        } else {
            begin.setNextInstruction(commands.getFirst().getRuntimeCommand().getInstructions().getFirst());
        }

        end.setNextInstruction(nextCommand.getRuntimeCommand().getInstructions().getFirst());
    }
}
