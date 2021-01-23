package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;


public class MgLocalVariableExpression extends MgExpression {
    @Optional @Link
    private MgInstanceVariable variable;

    public MgLocalVariableExpression() {
    }

    public MgLocalVariableExpression(MgInstanceVariable variable) {
        this.variable = variable;
    }

    public MgInstanceVariable getVariable() {
        return variable;
    }

    public void setVariable(MgInstanceVariable variable) {
        this.variable = variable;
    }
}
