package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.components.definitions.MgLunaryOperator;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.text.Text;


public abstract class MgInt32LunaryOperator extends MgLunaryOperator implements MgBuildinRunnable {
    public MgInt32LunaryOperator() {
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
        MgInt32Object rightObject = (MgInt32Object) task.getObject(getRight());
        int right = rightObject.getValue();
        int result = compute(right);
        task.setObject(getResult(), new MgInt32Object(result));
    }

    protected abstract int compute(int right);
}
