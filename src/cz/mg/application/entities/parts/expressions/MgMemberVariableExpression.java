package cz.mg.application.entities.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;


public class MgMemberVariableExpression extends MgMemberExpression {
    @Optional @Link
    private MgInstanceVariable variable;

    public MgMemberVariableExpression() {
    }

    public MgInstanceVariable getVariable() {
        return variable;
    }

    public void setVariable(MgInstanceVariable variable) {
        this.variable = variable;
    }
}
