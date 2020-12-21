package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public class MgTryCommand extends MgCommand {
    @Mandatory @Part
    private final List<MgCommand> commands = new List<>();

    public MgTryCommand() {
    }

    public List<MgCommand> getCommands() {
        return commands;
    }
}
