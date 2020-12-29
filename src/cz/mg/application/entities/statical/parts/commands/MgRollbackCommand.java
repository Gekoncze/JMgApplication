package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;


public class MgRollbackCommand extends MgCommand {
    @Optional @Part
    private MgExpression expression;

    public MgRollbackCommand() {
    }

    public MgExpression getExpression() {
        return expression;
    }

    public void setExpression(MgExpression expression) {
        this.expression = expression;
    }
}
