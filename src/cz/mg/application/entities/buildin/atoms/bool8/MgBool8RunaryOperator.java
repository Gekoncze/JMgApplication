package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.text.Text;


public abstract class MgBool8RunaryOperator extends MgRunaryOperator implements MgBuildinRunnable {
    public MgBool8RunaryOperator() {
        MgVariable left = new MgVariable();
        left.setDefinition(MgBool8.getInstance());
        left.setName(new Text("left"));
        setLeft(left);

        MgVariable result = new MgVariable();
        result.setDefinition(MgBool8.getInstance());
        result.setName(new Text("result"));
        setResult(result);
    }

    @Override
    public void run(MgTask task) {
        MgBool8Object leftObject = (MgBool8Object) task.getObject(getLeft());
        boolean left = leftObject.getValue();
        boolean result = compute(left);
        task.setObject(getResult(), new MgBool8Object(result));
    }

    protected abstract boolean compute(boolean left);
}
