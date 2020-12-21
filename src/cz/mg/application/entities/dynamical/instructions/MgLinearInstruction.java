package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;


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
}
