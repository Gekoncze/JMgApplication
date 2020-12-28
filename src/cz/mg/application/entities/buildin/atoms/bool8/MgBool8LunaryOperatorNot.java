package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.collections.text.Text;


public class MgBool8LunaryOperatorNot extends MgBool8LunaryOperator {
    private static MgBool8LunaryOperatorNot instance;

    public static MgBool8LunaryOperatorNot getInstance() {
        if(instance == null) instance = new MgBool8LunaryOperatorNot();
        return instance;
    }

    private MgBool8LunaryOperatorNot() {
        setName(new Text("not"));
    }

    @Override
    protected boolean compute(boolean right) {
        return !right;
    }
}
