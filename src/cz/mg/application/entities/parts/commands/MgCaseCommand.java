package cz.mg.application.entities.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.parts.commands.interfaces.MgDependentCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgMultiLineCommand;
import cz.mg.application.entities.parts.expressions.MgExpression;


public class MgCaseCommand extends MgBlockCommand implements MgMultiLineCommand, MgDependentCommand {
    @Optional @Part
    private MgExpression expression;

    public MgCaseCommand() {
    }

    public MgExpression getExpression() {
        return expression;
    }

    public void setExpression(MgExpression expression) {
        this.expression = expression;
    }
}
