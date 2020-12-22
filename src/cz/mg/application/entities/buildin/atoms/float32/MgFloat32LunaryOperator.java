package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.components.definitions.MgLunaryOperator;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.text.Text;


public abstract class MgFloat32LunaryOperator extends MgLunaryOperator implements MgBuildinRunnable {
    public MgFloat32LunaryOperator() {
        MgVariable right = new MgVariable();
        right.setDefinition(MgFloat32.getInstance());
        right.setName(new Text("right"));
        setRight(right);

        MgVariable result = new MgVariable();
        result.setDefinition(MgFloat32.getInstance());
        result.setName(new Text("result"));
        setResult(result);
    }

    @Override
    public void run(MgTask task) {
        MgFloat32Object rightObject = (MgFloat32Object) task.getObject(getRight());
        int right = rightObject.getValue();
        int result = compute(right);
        task.setObject(getResult(), new MgFloat32Object(result));
    }

    protected abstract int compute(int right);
}
