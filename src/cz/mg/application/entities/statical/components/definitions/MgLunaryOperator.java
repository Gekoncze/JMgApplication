package cz.mg.application.entities.statical.components.definitions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;


public class MgLunaryOperator extends MgOperator {
    @Optional @Part
    private MgInstanceVariable right;

    @Optional @Part
    private MgInstanceVariable result;

    public MgLunaryOperator() {
    }

    public MgInstanceVariable getRight() {
        return right;
    }

    public void setRight(MgInstanceVariable right) {
        this.right = right;
    }

    public MgInstanceVariable getResult() {
        return result;
    }

    public void setResult(MgInstanceVariable result) {
        this.result = result;
    }
}
