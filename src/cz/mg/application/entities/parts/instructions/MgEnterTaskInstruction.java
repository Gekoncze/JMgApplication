package cz.mg.application.entities.parts.instructions;

import cz.mg.application.entities.architecture.MgThread;
import cz.mg.application.entities.objects.MgTask;


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
