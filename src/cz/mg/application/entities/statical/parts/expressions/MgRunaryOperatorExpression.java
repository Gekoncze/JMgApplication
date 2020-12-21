package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.components.definitions.MgRunaryOperator;


public class MgRunaryOperatorExpression extends MgExpression {
    @Optional @Link
    private MgRunaryOperator operator;

    @Optional @Part
    private MgExpression left;

    public MgRunaryOperatorExpression() {
    }

    public MgRunaryOperator getOperator() {
        return operator;
    }

    public void setOperator(MgRunaryOperator operator) {
        this.operator = operator;
    }

    public MgExpression getLeft() {
        return left;
    }

    public void setLeft(MgExpression left) {
        this.left = left;
    }
}
