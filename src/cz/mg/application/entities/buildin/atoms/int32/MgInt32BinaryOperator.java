package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBinaryOperator;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.text.Text;


public abstract class MgInt32BinaryOperator extends MgBinaryOperator implements MgBuildinRunnable {
    public MgInt32BinaryOperator() {
        MgVariable left = new MgVariable();
        left.setDefinition(MgInt32.getInstance());
        left.setName(new Text("left"));
        setLeft(left);

        MgVariable right = new MgVariable();
        right.setDefinition(MgInt32.getInstance());
        right.setName(new Text("right"));
        setRight(right);

        MgVariable result = new MgVariable();
        result.setDefinition(MgInt32.getInstance());
        result.setName(new Text("result"));
        setResult(result);
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
