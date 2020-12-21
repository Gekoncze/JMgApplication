package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.components.definitions.MgBinaryOperator;


public class MgBinaryOperatorExpression extends MgExpression {
    @Optional @Link
    private MgBinaryOperator operator;

    @Optional @Part
    private MgExpression left;

    @Optional @Part
    private MgExpression right;

    public MgBinaryOperatorExpression() {
    }

    public MgBinaryOperator getOperator() {
        return operator;
    }

    public void setOperator(MgBinaryOperator operator) {
        this.operator = operator;
    }

    public MgExpression getLeft() {
        return left;
    }

    public void setLeft(MgExpression left) {
        this.left = left;
    }

    public MgExpression getRight() {
        return right;
    }

    public void setRight(MgExpression right) {
        this.right = right;
    }
}
