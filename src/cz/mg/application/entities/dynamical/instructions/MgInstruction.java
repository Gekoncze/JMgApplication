package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.dynamical.MgDynamicalEntity;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public abstract class MgInstruction extends MgDynamicalEntity {
    @Optional @Link
    private MgCommand command;

    public MgInstruction() {
    }

    public MgCommand getCommand() {
        return command;
    }

    public void setCommand(MgCommand command) {
        this.command = command;
    }

    public abstract void run(MgTask task);
}
