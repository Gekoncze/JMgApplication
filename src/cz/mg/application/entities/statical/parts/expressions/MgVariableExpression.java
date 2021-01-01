package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.statical.parts.MgVariable;


public class MgVariableExpression extends MgExpression {
    @Optional @Link
    private MgVariable variable;

    public MgVariableExpression() {
    }

    public MgVariable getVariable() {
        return variable;
    }

    public void setVariable(MgVariable variable) {
        this.variable = variable;
    }
}
