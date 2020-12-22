package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.statical.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.text.Text;


public abstract class MgFloat32RunaryOperator extends MgRunaryOperator implements MgBuildinRunnable {
    public MgFloat32RunaryOperator() {
        MgVariable left = new MgVariable();
        left.setDefinition(MgFloat32.getInstance());
        left.setName(new Text("left"));
        setLeft(left);

        MgVariable result = new MgVariable();
        result.setDefinition(MgFloat32.getInstance());
        result.setName(new Text("result"));
        setResult(result);
    }

    @Override
    public void run(MgTask task) {
        MgFloat32Object leftObject = (MgFloat32Object) task.getObject(getLeft());
        int left = leftObject.getValue();
        int result = compute(left);
        task.setObject(getResult(), new MgFloat32Object(result));
    }

    protected abstract int compute(int left);
}
