package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;


public class MgMemberExpression extends MgExpression {
    @Optional @Part
    private MgExpression parent;

    @Optional @Part
    private MgExpression child;

    public MgMemberExpression() {
    }

    public MgExpression getParent() {
        return parent;
    }

    public void setParent(MgExpression parent) {
        this.parent = parent;
    }

    public MgExpression getChild() {
        return child;
    }

    public void setChild(MgExpression child) {
        this.child = child;
    }
}
