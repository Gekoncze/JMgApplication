package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Parent;
import cz.mg.annotations.storage.Part;
import cz.mg.application.MgApplication;
import cz.mg.application.entities.dynamical.objects.MgObject;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.parts.commands.MgCatchCommand;
import cz.mg.application.entities.statical.parts.commands.MgCheckpointCommand;
import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.collections.list.List;


public class MgThread implements Runnable {
    @Mandatory @Parent
    private final MgApplication application;

    @Mandatory @Part
    private final List<MgTask> stack = new List<>();

    @Optional @Part
    private MgObject exception;

    public MgThread(MgApplication application) {
        this.application = application;
    }

    public MgApplication getApplication() {
        return application;
    }

    public List<MgTask> getStack() {
        return stack;
    }

    public MgObject getException() {
        return exception;
    }

    public void setException(MgObject exception) {
        this.exception = exception;
    }

    @Override
    public void run() {
        MgTask task = stack.getLast();
        if(exception == null){
            task.getInstruction().run(task);
        } else {
            handleException(task);
        }
    }

    private void handleException(MgTask task){
        while(task != null){
            MgCommand command = task.getInstruction().getCommand();
            while(command != null){
                if(command instanceof MgCheckpointCommand){
                    MgCheckpointCommand checkpointCommand = (MgCheckpointCommand) command;
                    for(MgCatchCommand catchCommand : checkpointCommand.getCatchCommands()){
                        if(exception.getType().is(catchCommand.getVariable().getDefinition().getType())){
                            task.setInstruction(catchCommand.getInstructions().getFirst());
                            exception = null;
                            return;
                        }
                    }
                }

                command = command.getParent();
            }

            stack.removeLast();
            task = stack.getLast();
        }

        throw new RuntimeException("Uncaught exception.");
    }
}
