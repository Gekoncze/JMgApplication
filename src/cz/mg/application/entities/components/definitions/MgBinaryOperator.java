package cz.mg.application.entities.components.definitions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.collections.list.List;


public class MgBinaryOperator extends MgOperator {
    @Optional @Part
    private MgInstanceVariable left;

    @Optional @Part
    private MgInstanceVariable right;

    @Optional @Part
    private MgInstanceVariable result;

    public MgBinaryOperator() {
    }

    @Override
    public List<MgInstanceVariable> getInput() {
        if(left == null && right == null) return new List<>();
        if(left == null) return new List<>(right);
        if(right == null) return new List<>(left);
        return new List<>(left, right);
    }

    @Override
    public List<MgInstanceVariable> getOutput() {
        if(result == null) return new List<>();
        return new List<>(result);
    }

    public MgInstanceVariable getLeft() {
        return left;
    }

    public void setLeft(MgInstanceVariable left) {
        this.left = left;
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
