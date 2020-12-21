package cz.mg.application.entities.statical.components.definitions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.MgVariable;


public class MgBinaryOperator extends MgOperator {
    @Optional @Part
    private MgVariable left;

    @Optional @Part
    private MgVariable right;

    @Optional @Part
    private MgVariable result;

    public MgBinaryOperator() {
    }

    public MgVariable getLeft() {
        return left;
    }

    public void setLeft(MgVariable left) {
        this.left = left;
    }

    public MgVariable getRight() {
        return right;
    }

    public void setRight(MgVariable right) {
        this.right = right;
    }

    public MgVariable getResult() {
        return result;
    }

    public void setResult(MgVariable result) {
        this.result = result;
    }
}
