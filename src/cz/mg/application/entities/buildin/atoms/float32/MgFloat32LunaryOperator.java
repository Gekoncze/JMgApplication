package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.application.entities.buildin.MgBuildinLunaryOperator;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.MgDefinition;


public abstract class MgFloat32LunaryOperator extends MgBuildinLunaryOperator {
    public MgFloat32LunaryOperator() {
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
        MgFloat32Object rightObject = (MgFloat32Object) task.getObject(getRight());
        float right = rightObject.getValue();
        float result = compute(right);
        task.setObject(getResult(), new MgFloat32Object(result));
    }

    protected abstract float compute(float right);
}
