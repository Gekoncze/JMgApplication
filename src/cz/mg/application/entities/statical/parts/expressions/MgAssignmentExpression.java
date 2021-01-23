package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;


public class MgAssignmentExpression extends MgExpression {
    @Optional @Part
    private MgExpression left;

    @Optional @Part
    private MgExpression right;

    public MgAssignmentExpression() {
    }

    public MgAssignmentExpression(MgExpression left, MgExpression right) {
        this.left = left;
        this.right = right;
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
