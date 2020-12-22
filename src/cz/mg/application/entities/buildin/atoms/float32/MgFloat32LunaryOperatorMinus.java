package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.collections.text.Text;


public class MgFloat32LunaryOperatorMinus extends MgFloat32LunaryOperator {
    private static MgFloat32LunaryOperatorMinus instance;

    public static MgFloat32LunaryOperatorMinus getInstance() {
        if(instance == null) instance = new MgFloat32LunaryOperatorMinus();
        return instance;
    }

    private MgFloat32LunaryOperatorMinus() {
        setName(new Text("-"));
    }


    @Override
    protected int compute(int right) {
        return -right;
    }
}
