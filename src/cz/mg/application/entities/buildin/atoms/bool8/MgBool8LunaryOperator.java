package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.application.entities.buildin.MgBuildinLunaryOperator;
import cz.mg.application.entities.objects.MgTask;
import cz.mg.application.entities.components.MgDefinition;


public abstract class MgBool8LunaryOperator extends MgBuildinLunaryOperator {
    public MgBool8LunaryOperator() {
    }

    @Override
    protected MgDefinition getRightInputDefinition() {
        return MgBool8.getInstance();
    }

    @Override
    protected MgDefinition getOutputDefinition() {
        return MgBool8.getInstance();
    }

    @Override
    public void run(MgTask task) {
        MgBool8Object rightObject = (MgBool8Object) task.getObject(getRight());
        boolean right = rightObject.getValue();
        boolean result = compute(right);
        task.setObject(getResult(), new MgBool8Object(result));
    }

    protected abstract boolean compute(boolean right);
}
