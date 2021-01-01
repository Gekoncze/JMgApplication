package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.runtime.MgRuntimeEntity;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.collections.list.List;


public class MgThread extends MgRuntimeEntity implements Runnable {
    public static MgThread getInstance(){
        return MgCore.getInstance().getThread();
    }

    @Mandatory @Part
    private final List<MgTask> stack = new List<>();

    @Optional @Link
    private MgTask task;

    public MgThread() {
    }

    public List<MgTask> getStack() {
        return stack;
    }

    public MgTask getTask() {
        return task;
    }

    public void setTask(MgTask task) {
        this.task = task;
    }

    @Override
    public void run() {
        if(task != null){
            task.getInstruction().run(task);
        }
    }
}
