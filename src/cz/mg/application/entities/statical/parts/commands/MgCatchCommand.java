package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgDependentCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgMultiLineCommand;


public class MgCatchCommand extends MgBlockCommand implements MgMultiLineCommand, MgDependentCommand {
    @Optional @Link
    private MgVariable variable = new MgVariable();

    public MgCatchCommand() {
    }

    public MgVariable getVariable() {
        return variable;
    }

    public void setVariable(MgVariable variable) {
        this.variable = variable;
    }
}
