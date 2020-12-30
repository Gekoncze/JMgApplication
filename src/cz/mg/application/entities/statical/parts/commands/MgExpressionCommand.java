package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgSingleLineCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;


public class MgExpressionCommand extends MgCommand implements MgSingleLineCommand, MgStandaloneCommand {
    @Optional @Part
    private MgExpression expression;

    public MgExpressionCommand() {
    }

    public MgExpression getExpression() {
        return expression;
    }

    public void setExpression(MgExpression expression) {
        this.expression = expression;
    }
}
