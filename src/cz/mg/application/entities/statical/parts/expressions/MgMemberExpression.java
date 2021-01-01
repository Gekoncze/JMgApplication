package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;


public abstract class MgMemberExpression extends MgExpression {
    @Optional @Part
    private MgExpression parent;

    public MgMemberExpression() {
    }

    public MgExpression getParent() {
        return parent;
    }

    public void setParent(MgExpression parent) {
        this.parent = parent;
    }
}
