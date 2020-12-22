package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;


public class MgBuildinRunnableInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgBuildinRunnable buildinRunnable;

    public MgBuildinRunnableInstruction(MgBuildinRunnable buildinRunnable) {
        this.buildinRunnable = buildinRunnable;
    }

    @Override
    public void run(MgTask task) {
        buildinRunnable.run(task);
        task.setInstruction(getNextInstruction());
    }
}
