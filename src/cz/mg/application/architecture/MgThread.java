package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
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

    public MgThread() {
    }

    public List<MgTask> getStack() {
        return stack;
    }

    @Override
    public void run() {
        MgTask task = stack.getLast();
        if(task != null){
            task.getInstruction().run(task);
        }
    }
}
