package cz.mg.application.entities.runtime.instructions;

import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.objects.MgTask;


public class MgEnterTaskInstruction extends MgLinearInstruction {
    public MgEnterTaskInstruction() {
    }

    @Override
    public void run(MgTask task) {
        MgThread thread = MgThread.getInstance();
        thread.setTask(thread.getStack().getLast());
        super.run(task);
    }
}
