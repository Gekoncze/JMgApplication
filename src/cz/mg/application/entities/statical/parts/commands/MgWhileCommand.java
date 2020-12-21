package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;
import cz.mg.collections.list.List;


public class MgWhileCommand extends MgCommand {
    @Optional @Part
    private MgExpression expression;

    @Mandatory @Part
    private List<MgCommand> commands = new List<>();

    public MgWhileCommand() {
    }

    public MgExpression getExpression() {
        return expression;
    }

    public void setExpression(MgExpression expression) {
        this.expression = expression;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }
}
