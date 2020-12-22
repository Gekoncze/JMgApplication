package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.collections.text.Text;


public class MgInt32LunaryOperatorPlus extends MgInt32LunaryOperator {
    private static MgInt32LunaryOperatorPlus instance;

    public static MgInt32LunaryOperatorPlus getInstance() {
        if(instance == null) instance = new MgInt32LunaryOperatorPlus();
        return instance;
    }

    private MgInt32LunaryOperatorPlus() {
        setName(new Text("+"));
    }


    @Override
    protected int compute(int right) {
        return +right;
    }
}
