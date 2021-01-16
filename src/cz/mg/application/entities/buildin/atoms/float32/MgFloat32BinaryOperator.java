package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.application.entities.buildin.MgBuildinBinaryOperator;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.MgDefinition;


public abstract class MgFloat32BinaryOperator extends MgBuildinBinaryOperator {
    public MgFloat32BinaryOperator() {
    }

    @Override
    protected MgDefinition getLeftInputDefinition() {
        return MgFloat32.getInstance();
    }

    @Override
    protected MgDefinition getRightInputDefinition() {
        return MgFloat32.getInstance();
    }

    @Override
    protected MgDefinition getOutputDefinition() {
        return MgFloat32.getInstance();
    }

    @Override
    public void run(MgTask task) {
        MgFloat32Object leftObject = (MgFloat32Object) task.getObject(getLeft());
        MgFloat32Object rightObject = (MgFloat32Object) task.getObject(getRight());
        float left = leftObject.getValue();
        float right = rightObject.getValue();
        float result = compute(left, right);
        task.setObject(getResult(), new MgFloat32Object(result));
    }

    protected abstract float compute(float left, float right);
}
