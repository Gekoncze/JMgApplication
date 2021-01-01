package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.statical.parts.MgVariable;


public class MgMemberVariableExpression extends MgMemberExpression {
    @Optional @Link
    private MgVariable variable;

    public MgMemberVariableExpression() {
    }

    public MgVariable getVariable() {
        return variable;
    }

    public void setVariable(MgVariable variable) {
        this.variable = variable;
    }
}
