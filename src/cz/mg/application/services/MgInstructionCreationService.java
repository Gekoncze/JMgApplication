package cz.mg.application.services;

import cz.mg.application.entities.dynamical.instructions.MgGotoInstruction;
import cz.mg.application.entities.dynamical.instructions.dummy.MgBeginDummyInstruction;
import cz.mg.application.entities.dynamical.instructions.dummy.MgEndDummyInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.commands.*;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;

import static cz.mg.application.services.MgInstructionOptimizationService.optimize;
import static cz.mg.application.services.MgInstructionValidationService.validate;


public class MgInstructionCreationService extends MgInstructionService {
    public static void create(MgProcedure procedure){
        check(procedure);
        createSequence(procedure.getCommands(), null);
        optimize(procedure);
        validate(procedure);
    }

    private static void createSequence(List<MgCommand> commands, MgCommand nextCommand){
        for(MgCommand command : reverse(commands)){
            create(command, nextCommand);
            nextCommand = command;
        }
    }

    private static List<MgCommand> reverse(List<MgCommand> commands){
        List<MgCommand> reversed = new List<>();
        for(MgCommand command : commands){
            reversed.addFirst(command);
        }
        return reversed;
    }

    private static void create(MgCommand command, MgCommand nextCommand){
        if(command instanceof MgBreakCommand){
            create((MgBreakCommand) command, nextCommand);
        }

        if(command instanceof MgCaseCommand){
            create((MgCaseCommand) command, nextCommand);
        }

        if(command instanceof MgCatchCommand){
            create((MgCatchCommand) command, nextCommand);
        }

        if(command instanceof MgCheckpointCommand){
            create((MgCheckpointCommand) command, nextCommand);
        }

        if(command instanceof MgContinueCommand){
            create((MgContinueCommand) command, nextCommand);
        }

        if(command instanceof MgExpressionCommand){
            create((MgExpressionCommand) command, nextCommand);
        }

        if(command instanceof MgReturnCommand){
            create((MgReturnCommand) command, nextCommand);
        }

        if(command instanceof MgRollbackCommand){
            create((MgRollbackCommand) command, nextCommand);
        }

        if(command instanceof MgSwitchCommand){
            create((MgSwitchCommand) command, nextCommand);
        }

        if(command instanceof MgTryCommand){
            create((MgTryCommand) command, nextCommand);
        }

        if(command instanceof MgWhileCommand){
            create((MgWhileCommand) command, nextCommand);
        }
    }

    private static void create(MgBreakCommand command, MgCommand nextCommand){
        MgCommand target = (MgCommand) command.getTarget();
        command.setInstructions(new Array<>(
            new MgGotoInstruction(command, target.getInstructions().getLast())
        ));
    }

    private static void create(MgCaseCommand command, MgCommand nextCommand){
        createDummyInstructions(command);
        createSequence(command.getCommands(), nextCommand);
        connectDummyInstructions(command, command.getCommands(), nextCommand);
    }

    private static void create(MgCatchCommand command, MgCommand nextCommand){
        createDummyInstructions(command);
        createSequence(command.getCommands(), nextCommand);
        connectDummyInstructions(command, command.getCommands(), nextCommand);
    }

    private static void create(MgCheckpointCommand command, MgCommand nextCommand){
        //todo;
    }

    private static void create(MgContinueCommand command, MgCommand nextCommand){
        MgCommand target = (MgCommand) command.getTarget();
        command.setInstructions(new Array<>(
            new MgGotoInstruction(command, target.getInstructions().getFirst())
        ));
    }

    private static void create(MgExpressionCommand command, MgCommand nextCommand){
        //todo;
    }

    private static void create(MgReturnCommand command, MgCommand nextCommand){
        //todo;
    }

    private static void create(MgRollbackCommand command, MgCommand nextCommand){
        //todo;
    }

    private static void create(MgSwitchCommand command, MgCommand nextCommand){
        //todo;
    }

    private static void create(MgTryCommand command, MgCommand nextCommand){
        //todo;
    }

    private static void create(MgWhileCommand command, MgCommand nextCommand){
        //todo;
    }

    private static void createDummyInstructions(MgCommand command){
        command.setInstructions(new Array<>(
            new MgBeginDummyInstruction(command),
            new MgEndDummyInstruction(command)
        ));
    }

    private static void connectDummyInstructions(MgCommand command, List<MgCommand> commands, MgCommand nextCommand){
        MgBeginDummyInstruction begin = (MgBeginDummyInstruction) command.getInstructions().getFirst();
        MgEndDummyInstruction end = (MgEndDummyInstruction) command.getInstructions().getLast();

        if(commands.isEmpty()){
            begin.setNextInstruction(end);
        } else {
            begin.setNextInstruction(commands.getFirst().getInstructions().getFirst());
        }

        end.setNextInstruction(nextCommand.getInstructions().getFirst());
    }
}
