package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.application.entities.buildin.MgBuildinBinaryOperator;
import cz.mg.application.entities.objects.MgTask;
import cz.mg.application.entities.components.MgDefinition;


public abstract class MgBool8BinaryOperator extends MgBuildinBinaryOperator {
    public MgBool8BinaryOperator() {
    }

    @Override
    protected MgDefinition getLeftInputDefinition() {
        return MgBool8.getInstance();
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
        MgBool8Object leftObject = (MgBool8Object) task.getObject(getLeft());
        MgBool8Object rightObject = (MgBool8Object) task.getObject(getRight());
        boolean left = leftObject.getValue();
        boolean right = rightObject.getValue();
        boolean result = compute(left, right);
        task.setObject(getResult(), new MgBool8Object(result));
    }

    protected abstract boolean compute(boolean left, boolean right);
}
