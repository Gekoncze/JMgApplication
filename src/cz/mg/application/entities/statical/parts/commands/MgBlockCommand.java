package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.collections.list.List;


public abstract class MgBlockCommand extends MgCommand {
    @Mandatory @Part
    private final List<MgStandaloneCommand> commands = new List<>();

    public MgBlockCommand() {
    }

    public List<MgStandaloneCommand> getCommands() {
        return commands;
    }
}
