package cz.mg.application.entities.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.application.entities.parts.variables.MgVariable;
import cz.mg.collections.list.List;


public class MgCheckpoint extends MgPart {
    @Optional @Link
    private MgVariable variable;

    @Mandatory @Part
    private final List<MgStandaloneCommand> commands = new List<>();

    public MgCheckpoint() {
    }

    public MgVariable getVariable() {
        return variable;
    }

    public void setVariable(MgVariable variable) {
        this.variable = variable;
    }

    public List<MgStandaloneCommand> getCommands() {
        return commands;
    }
}
