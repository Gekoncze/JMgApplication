package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.application.entities.buildin.MgBuildinRunaryOperator;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.MgDefinition;


public abstract class MgInt32RunaryOperator extends MgBuildinRunaryOperator {
    public MgInt32RunaryOperator() {
    }

    @Override
    protected MgDefinition getLeftInputDefinition() {
        return MgInt32.getInstance();
    }

    @Override
    protected MgDefinition getOutputDefinition() {
        return MgInt32.getInstance();
    }

    @Override
    public void run(MgTask task) {
        MgInt32Object leftObject = (MgInt32Object) task.getObject(getLeft());
        int left = leftObject.getValue();
        int result = compute(left);
        task.setObject(getResult(), new MgInt32Object(result));
    }

    protected abstract int compute(int left);
}
