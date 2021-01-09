package cz.mg.application.entities.statical.components.definitions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;


public class MgRunaryOperator extends MgOperator {
    @Optional @Part
    private MgInstanceVariable left;

    @Optional @Part
    private MgInstanceVariable result;

    public MgRunaryOperator() {
    }

    public MgInstanceVariable getLeft() {
        return left;
    }

    public void setLeft(MgInstanceVariable left) {
        this.left = left;
    }

    public MgInstanceVariable getResult() {
        return result;
    }

    public void setResult(MgInstanceVariable result) {
        this.result = result;
    }
}
