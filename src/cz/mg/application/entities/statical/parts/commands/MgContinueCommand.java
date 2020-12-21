package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;


public class MgContinueCommand extends MgCommand {
    @Optional @Link
    private MgCommand target;

    public MgContinueCommand() {
    }

    public MgCommand getTarget() {
        return target;
    }

    public void setTarget(MgCommand target) {
        this.target = target;
    }
}
