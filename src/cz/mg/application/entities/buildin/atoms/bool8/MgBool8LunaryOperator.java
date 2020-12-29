package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.components.definitions.MgLunaryOperator;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.text.Text;


public abstract class MgBool8LunaryOperator extends MgLunaryOperator implements MgBuildinRunnable {
    public MgBool8LunaryOperator() {
        MgVariable right = new MgVariable();
        right.setDefinition(MgBool8.getInstance());
        right.setName(new Text("right"));
        setRight(right);

        MgVariable result = new MgVariable();
        result.setDefinition(MgBool8.getInstance());
        result.setName(new Text("result"));
        setResult(result);
    }

    @Override
    public void run(MgTask task) {
        MgBool8Object rightObject = (MgBool8Object) task.getObject(getRight());
        boolean right = rightObject.getValue();
        boolean result = compute(right);
        task.setObject(getResult(), new MgBool8Object(result));
    }

    protected abstract boolean compute(boolean right);
}