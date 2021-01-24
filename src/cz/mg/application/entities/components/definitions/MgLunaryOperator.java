package cz.mg.application.entities.components.definitions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.collections.list.List;


public class MgLunaryOperator extends MgOperator {
    @Optional @Part
    private MgInstanceVariable right;

    @Optional @Part
    private MgInstanceVariable result;

    public MgLunaryOperator() {
    }

    @Override
    public List<MgInstanceVariable> getInput() {
        if(right == null) return new List<>();
        return new List<>(right);
    }

    @Override
    public List<MgInstanceVariable> getOutput() {
        if(result == null) return new List<>();
        return new List<>(result);
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
