package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.application.entities.buildin.MgBuildinRunaryOperator;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.MgDefinition;


public abstract class MgFloat32RunaryOperator extends MgBuildinRunaryOperator {
    public MgFloat32RunaryOperator() {
    }

    @Override
    protected MgDefinition getLeftInputDefinition() {
        return MgFloat32.getInstance();
    }

    @Override
    protected MgDefinition getOutputDefinition() {
        return MgFloat32.getInstance();
    }

    @Override
    public void run(MgTask task) {
        MgFloat32Object leftObject = (MgFloat32Object) task.getObject(getLeft());
        float left = leftObject.getValue();
        float result = compute(left);
        task.setObject(getResult(), new MgFloat32Object(result));
    }

    protected abstract float compute(float left);
}
