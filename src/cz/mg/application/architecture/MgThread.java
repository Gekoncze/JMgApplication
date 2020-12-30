package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.runtime.MgRuntimeEntity;
import cz.mg.application.entities.runtime.objects.MgObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.collections.list.List;

import static cz.mg.application.services.MgExceptionHandlingService.handleException;


public class MgThread extends MgRuntimeEntity implements Runnable {
    public static MgThread getInstance(){
        return MgCore.getInstance().getThread();
    }

    @Mandatory @Part
    private final List<MgTask> stack = new List<>();

    @Optional @Part
    private MgObject exception;

    public MgThread() {
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
        if(task != null){
            if(exception == null){
                task.getInstruction().run(task);
            } else {
                handleException(stack, task, exception);
                exception = null;
            }
        }
    }
}
