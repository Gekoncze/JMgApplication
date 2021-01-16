package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.application.entities.buildin.MgBuildinBinaryOperator;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.MgDefinition;


public abstract class MgInt32BinaryOperator extends MgBuildinBinaryOperator {
    public MgInt32BinaryOperator() {
    }

    @Override
    protected MgDefinition getLeftInputDefinition() {
        return MgInt32.getInstance();
    }

    @Override
    protected MgDefinition getRightInputDefinition() {
        return MgInt32.getInstance();
    }

    @Override
    protected MgDefinition getOutputDefinition() {
        return MgInt32.getInstance();
    }

    @Override
    public void run(MgTask task) {
        MgInt32Object leftObject = (MgInt32Object) task.getObject(getLeft());
        MgInt32Object rightObject = (MgInt32Object) task.getObject(getRight());
        int left = leftObject.getValue();
        int right = rightObject.getValue();
        int result = compute(left, right);
        task.setObject(getResult(), new MgInt32Object(result));
    }

    protected abstract int compute(int left, int right);
}
