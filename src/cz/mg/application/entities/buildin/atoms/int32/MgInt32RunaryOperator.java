package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.text.Text;


public abstract class MgInt32RunaryOperator extends MgRunaryOperator implements MgBuildinRunnable {
    public MgInt32RunaryOperator() {
        MgVariable left = new MgVariable();
        left.setDefinition(MgInt32.getInstance());
        left.setName(new Text("left"));
        setLeft(left);

        MgVariable result = new MgVariable();
        result.setDefinition(MgInt32.getInstance());
        result.setName(new Text("result"));
        setResult(result);
    }

    @Override
    public void run(MgTask task) {
        MgInt32Object leftObject = (MgInt32Object) task.getObject(getLeft());
        int left = leftObject.getValue();
        int result = compute(left);
        task.setObject(getResult(), new MgInt32Object(result));
    }

    protected abstract int compute(int left);
}
