package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgMultiLineCommand;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;
import cz.mg.collections.list.List;


public class MgCaseCommand extends MgCommand implements MgMultiLineCommand {
    @Optional @Part
    private MgExpression expression;

    @Mandatory @Part
    private final List<MgCommand> commands = new List<>();

    public MgCaseCommand() {
    }

    public MgExpression getExpression() {
        return expression;
    }

    public void setExpression(MgExpression expression) {
        this.expression = expression;
    }

    @Override
    public List<MgCommand> getCommands() {
        return commands;
    }
}
