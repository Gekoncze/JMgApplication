package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public abstract class MgLinearInstruction extends MgInstruction {
    @Optional @Link
    private MgInstruction nextInstruction;

    public MgLinearInstruction(MgCommand command) {
        this(command, null);
    }

    public MgLinearInstruction(MgCommand command, MgInstruction nextInstruction) {
        super(command);
        setNextInstruction(nextInstruction);
    }

    public MgInstruction getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstruction nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public void run(MgTask task) {
        task.setInstruction(getNextInstruction());
    }
}
