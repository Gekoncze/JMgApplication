package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.collections.text.Text;


public class MgFloat32LunaryOperatorPlus extends MgFloat32LunaryOperator {
    private static MgFloat32LunaryOperatorPlus instance;

    public static MgFloat32LunaryOperatorPlus getInstance() {
        if(instance == null) instance = new MgFloat32LunaryOperatorPlus();
        return instance;
    }

    private MgFloat32LunaryOperatorPlus() {
        setName(new Text("+"));
    }


    @Override
    protected int compute(int right) {
        return +right;
    }
}
