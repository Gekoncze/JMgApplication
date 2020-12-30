package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public class MgBuildinRunnableInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgBuildinRunnable runnable;

    public MgBuildinRunnableInstruction(MgCommand command, MgInstruction nextInstruction, MgBuildinRunnable runnable) {
        super(command, nextInstruction);
        this.runnable = runnable;
    }

    @Override
    public void run(MgTask task) {
        runnable.run(task);
        super.run(task);
    }
}
