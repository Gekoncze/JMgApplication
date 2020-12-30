package cz.mg.application.services;

import cz.mg.application.entities.runtime.objects.MgObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.commands.MgCatchCommand;
import cz.mg.application.entities.statical.parts.commands.MgCheckpointCommand;
import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.collections.list.List;


public class MgExceptionHandlingService {
// todo - original algorithm - remove after test is implemented and verified - both versions should give the same result
//
//    public static void handleException(List<MgTask> stack, MgTask task, MgObject exception){
//        while(task != null){
//            MgCommand command = task.getInstruction().getCommand();
//            while(command != null){
//                if(command instanceof MgCheckpointCommand){
//                    MgCheckpointCommand checkpointCommand = (MgCheckpointCommand) command;
//                    for(MgCatchCommand catchCommand : checkpointCommand.getCatchCommands()){
//                        if(exception.getType().is(catchCommand.getVariable().getDefinition().getType())){
//                            task.setInstruction(catchCommand.getRuntimeCommand().getInstructions().getFirst());
//                            task.setObject(catchCommand.getVariable(), exception);
//                            return;
//                        }
//                    }
//                }
//
//                command = command.getRuntimeCommand().getParent();
//            }
//
//            stack.removeLast();
//            task = stack.getLast();
//        }
//
//        throw new RuntimeException("Uncaught exception.");
//    }

    public static void handleException(List<MgTask> stack, MgTask task, MgObject exception){
        MgCatchCommand catchCommand = rollbackTasks(stack, task, exception);
        if(catchCommand != null){
            stack.getLast().setInstruction(catchCommand.getRuntimeCommand().getInstructions().getFirst());
            stack.getLast().setObject(catchCommand.getVariable(), exception);
        } else {
            throw new RuntimeException("Uncaught exception.");
        }
    }

    private static MgCatchCommand rollbackTasks(List<MgTask> stack, MgTask task, MgObject exception){
        while(task != null){
            MgCatchCommand catchCommand = rollbackCommands(stack, task.getInstruction().getCommand(), exception);
            if(catchCommand != null){
                return catchCommand;
            } else {
                stack.removeLast();
                task = stack.getLast();
            }
        }
        return null;
    }

    private static MgCatchCommand rollbackCommands(List<MgTask> stack, MgCommand command, MgObject exception){
        while(command != null){
            if(command instanceof MgCheckpointCommand){
                MgCheckpointCommand checkpointCommand = (MgCheckpointCommand) command;
                for(MgCatchCommand catchCommand : checkpointCommand.getCatchCommands()){
                    if(exception.getType().is(catchCommand.getVariable().getDefinition().getType())){
                        return catchCommand;
                    }
                }
            }

            command = command.getRuntimeCommand().getParent();
        }
        return null;
    }
}
