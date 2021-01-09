package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.collections.text.Text;


public abstract class MgBool8RunaryOperator extends MgRunaryOperator implements MgBuildinRunnable {
    public MgBool8RunaryOperator() {
        MgInstanceVariable left = new MgInstanceVariable();
        left.setDefinition(MgBool8.getInstance());
        left.setName(new Text("left"));
        setLeft(left);

        MgInstanceVariable result = new MgInstanceVariable();
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
