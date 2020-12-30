package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgBreakableCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgSingleLineCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgStandaloneCommand;


public class MgBreakCommand extends MgCommand implements MgSingleLineCommand, MgStandaloneCommand {
    @Optional @Link
    private MgBreakableCommand target;

    public MgBreakCommand() {
    }

    public MgBreakableCommand getTarget() {
        return target;
    }

    public void setTarget(MgBreakableCommand target) {
        this.target = target;
    }
}
