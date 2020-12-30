package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgMultiLineCommand;
import cz.mg.collections.list.List;


public class MgCatchCommand extends MgCommand implements MgMultiLineCommand {
    @Optional @Link
    private MgVariable variable = new MgVariable();

    @Mandatory @Part
    private final List<MgCommand> commands = new List<>();

    public MgCatchCommand() {
    }

    public MgVariable getVariable() {
        return variable;
    }

    public void setVariable(MgVariable variable) {
        this.variable = variable;
    }

    @Override
    public List<MgCommand> getCommands() {
        return commands;
    }
}
