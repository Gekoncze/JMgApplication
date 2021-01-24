package cz.mg.application.entities.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.parts.commands.interfaces.MgContinuableCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgSingleLineCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgStandaloneCommand;


public class MgContinueCommand extends MgCommand implements MgSingleLineCommand, MgStandaloneCommand {
    @Optional @Link
    private MgContinuableCommand target;

    public MgContinueCommand() {
    }

    public MgContinuableCommand getTarget() {
        return target;
    }

    public void setTarget(MgContinuableCommand target) {
        this.target = target;
    }
}
