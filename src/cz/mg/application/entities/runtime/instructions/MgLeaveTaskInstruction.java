package cz.mg.application.entities.runtime.instructions;

import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.objects.MgTask;


public class MgLeaveTaskInstruction extends MgTerminatingInstruction {
    public MgLeaveTaskInstruction() {
    }

    @Override
    public void run(MgTask task) {
        MgThread thread = MgThread.getInstance();
        thread.setTask(thread.getStack().getLastItem().getPrevious());
    }
}
