package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.collections.text.Text;


public abstract class MgFloat32RunaryOperator extends MgRunaryOperator implements MgBuildinRunnable {
    public MgFloat32RunaryOperator() {
        MgInstanceVariable left = new MgInstanceVariable();
        left.setDefinition(MgFloat32.getInstance());
        left.setName(new Text("left"));
        setLeft(left);

        MgInstanceVariable result = new MgInstanceVariable();
        result.setDefinition(MgFloat32.getInstance());
        result.setName(new Text("result"));
        setResult(result);
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
