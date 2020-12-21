package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public class MgFinallyCommand extends MgCommand {
    @Mandatory @Part
    private final List<MgCommand> commands = new List<>();

    public MgFinallyCommand() {
    }

    public List<MgCommand> getCommands() {
        return commands;
    }
}
