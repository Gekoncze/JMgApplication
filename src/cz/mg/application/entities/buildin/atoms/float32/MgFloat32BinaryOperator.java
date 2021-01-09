package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBinaryOperator;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.collections.text.Text;


public abstract class MgFloat32BinaryOperator extends MgBinaryOperator implements MgBuildinRunnable {
    public MgFloat32BinaryOperator() {
        MgInstanceVariable left = new MgInstanceVariable();
        left.setDefinition(MgFloat32.getInstance());
        left.setName(new Text("left"));
        setLeft(left);

        MgInstanceVariable right = new MgInstanceVariable();
        right.setDefinition(MgFloat32.getInstance());
        right.setName(new Text("right"));
        setRight(right);

        MgInstanceVariable result = new MgInstanceVariable();
        result.setDefinition(MgFloat32.getInstance());
        result.setName(new Text("result"));
        setResult(result);
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
