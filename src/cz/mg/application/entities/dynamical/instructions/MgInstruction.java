package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.dynamical.MgDynamicalEntity;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public abstract class MgInstruction extends MgDynamicalEntity {
    @Mandatory @Link
    private final MgCommand command;

    public MgInstruction(MgCommand command) {
        this.command = command;
    }

    public MgCommand getCommand() {
        return command;
    }

    public abstract void run(MgTask task);
}
