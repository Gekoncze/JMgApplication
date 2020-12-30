package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.MgRuntimeEntity;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public abstract class MgInstruction extends MgRuntimeEntity {
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
