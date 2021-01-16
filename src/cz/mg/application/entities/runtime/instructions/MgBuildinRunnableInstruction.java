package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;


public class MgBuildinRunnableInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgBuildinRunnable runnable;

    public MgBuildinRunnableInstruction(MgBuildinRunnable runnable) {
        this.runnable = runnable;
    }

    public MgBuildinRunnable getRunnable() {
        return runnable;
    }

    @Override
    public void run(MgTask task) {
        runnable.run(task);
        super.run(task);
    }
}
