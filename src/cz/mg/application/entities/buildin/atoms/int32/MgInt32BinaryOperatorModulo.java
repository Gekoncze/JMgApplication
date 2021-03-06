package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.collections.text.Text;


public class MgInt32BinaryOperatorModulo extends MgInt32BinaryOperator {
    private static MgInt32BinaryOperatorModulo instance;

    public static MgInt32BinaryOperatorModulo getInstance() {
        if(instance == null) instance = new MgInt32BinaryOperatorModulo();
        return instance;
    }

    private MgInt32BinaryOperatorModulo() {
        setName(new Text("%"));
    }


    @Override
    protected int compute(int left, int right) {
        return left % right;
    }
}
