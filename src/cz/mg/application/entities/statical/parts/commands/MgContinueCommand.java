package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgContinuableCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgSingleLineCommand;


public class MgContinueCommand extends MgCommand implements MgSingleLineCommand {
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
