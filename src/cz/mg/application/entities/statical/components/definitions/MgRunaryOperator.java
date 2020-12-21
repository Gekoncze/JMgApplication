package cz.mg.application.entities.statical.components.definitions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.MgVariable;


public class MgRunaryOperator extends MgOperator {
    @Optional @Part
    private MgVariable left;

    @Optional @Part
    private MgVariable result;

    public MgRunaryOperator() {
    }

    public MgVariable getLeft() {
        return left;
    }

    public void setLeft(MgVariable left) {
        this.left = left;
    }

    public MgVariable getResult() {
        return result;
    }

    public void setResult(MgVariable result) {
        this.result = result;
    }
}
