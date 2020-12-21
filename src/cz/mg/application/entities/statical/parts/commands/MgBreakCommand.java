package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;


public class MgBreakCommand extends MgCommand {
    @Optional @Link
    private MgCommand target;

    public MgBreakCommand() {
    }

    public MgCommand getTarget() {
        return target;
    }

    public void setTarget(MgCommand target) {
        this.target = target;
    }
}
