package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.components.definitions.MgLunaryOperator;


public class MgLunaryOperatorExpression extends MgExpression {
    @Optional @Link
    private MgLunaryOperator operator;

    @Optional @Part
    private MgExpression right;

    public MgLunaryOperatorExpression() {
    }

    public MgLunaryOperator getOperator() {
        return operator;
    }

    public void setOperator(MgLunaryOperator operator) {
        this.operator = operator;
    }

    public MgExpression getRight() {
        return right;
    }

    public void setRight(MgExpression right) {
        this.right = right;
    }
}
