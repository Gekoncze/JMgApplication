package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.objects.MgObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.MgCheckpoint;
import cz.mg.application.entities.statical.parts.MgVariable;


public class MgRollbackInstruction extends MgTerminatingInstruction {
    @Mandatory @Link
    private MgVariable variable;

    public MgRollbackInstruction(MgVariable variable) {
        this.variable = variable;
    }

    public MgVariable getVariable() {
        return variable;
    }

    public void setVariable(MgVariable variable) {
        this.variable = variable;
    }

    @Override
    public void run(MgTask task) {
        handleException(MgThread.getInstance(), task, task.getObject(variable));
    }

    public static void handleException(MgThread thread, MgTask task, MgObject exception){
        thread.setTask(null);
        while(task != null){
            MgCheckpoint checkpoint = findCheckpoint(task, exception);
            if(checkpoint != null){
                task.setInstruction(task.getType().getCheckpointInstructionMap().get(checkpoint));
                task.setObject(checkpoint.getVariable(), exception);
                thread.setTask(task);
            } else {
                thread.getStack().removeLast();
                task = thread.getStack().getLast();
            }
        }
        throw new RuntimeException("Uncaught exception.");
    }

    private static MgCheckpoint findCheckpoint(MgTask task, MgObject exception){
        for(MgCheckpoint checkpoint : task.getType().getProcedure().getCheckpoints()){
            if(exception.getType().is(checkpoint.getVariable().getDefinition().getType())){
                return checkpoint;
            }
        }
        return null;
    }
}
