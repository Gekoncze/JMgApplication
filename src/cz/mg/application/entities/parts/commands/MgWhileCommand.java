package cz.mg.application.entities.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.parts.commands.interfaces.MgBreakableCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgContinuableCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgMultiLineCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.application.entities.parts.expressions.MgExpression;


public class MgWhileCommand extends MgBlockCommand implements MgMultiLineCommand, MgStandaloneCommand, MgBreakableCommand, MgContinuableCommand {
    @Optional @Part
    private MgExpression expression;

    public MgWhileCommand() {
    }

    public MgExpression getExpression() {
        return expression;
    }

    public void setExpression(MgExpression expression) {
        this.expression = expression;
    }
}
