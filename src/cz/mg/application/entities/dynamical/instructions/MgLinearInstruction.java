package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.dynamical.objects.MgTask;


public abstract class MgLinearInstruction extends MgInstruction {
    @Optional @Link
    private MgInstruction nextInstruction;

    public MgLinearInstruction() {
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
