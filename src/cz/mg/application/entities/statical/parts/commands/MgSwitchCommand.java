package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgMultiLineCommand;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;
import cz.mg.collections.Clump;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;


public class MgSwitchCommand extends MgCommand implements MgMultiLineCommand {
    @Optional @Part
    private MgExpression expression;

    @Mandatory @Part
    private final List<MgCaseCommand> caseCommands = new List<>();

    public MgSwitchCommand() {
    }

    public MgExpression getExpression() {
        return expression;
    }

    public void setExpression(MgExpression expression) {
        this.expression = expression;
    }

    public List<MgCaseCommand> getCaseCommands() {
        return caseCommands;
    }

    @Override
    public Clump<MgCommand> getCommands() {
        return new Array<>(caseCommands);
    }
}
