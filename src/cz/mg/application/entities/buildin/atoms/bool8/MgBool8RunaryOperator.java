package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.application.entities.buildin.MgBuildinRunaryOperator;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.MgDefinition;


public abstract class MgBool8RunaryOperator extends MgBuildinRunaryOperator {
    public MgBool8RunaryOperator() {
    }

    @Override
    protected MgDefinition getLeftInputDefinition() {
        return MgBool8.getInstance();
    }

    @Override
    protected MgDefinition getOutputDefinition() {
        return MgBool8.getInstance();
    }

    @Override
    public void run(MgTask task) {
        MgBool8Object leftObject = (MgBool8Object) task.getObject(getLeft());
        boolean left = leftObject.getValue();
        boolean result = compute(left);
        task.setObject(getResult(), new MgBool8Object(result));
    }

    protected abstract boolean compute(boolean left);
}
