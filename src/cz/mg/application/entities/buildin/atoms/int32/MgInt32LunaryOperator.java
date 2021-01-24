package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.application.entities.buildin.MgBuildinLunaryOperator;
import cz.mg.application.entities.objects.MgTask;
import cz.mg.application.entities.components.MgDefinition;


public abstract class MgInt32LunaryOperator extends MgBuildinLunaryOperator {
    public MgInt32LunaryOperator() {
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
        MgInt32Object rightObject = (MgInt32Object) task.getObject(getRight());
        int right = rightObject.getValue();
        int result = compute(right);
        task.setObject(getResult(), new MgInt32Object(result));
    }

    protected abstract int compute(int right);
}
