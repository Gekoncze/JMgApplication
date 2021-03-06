package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.collections.text.Text;


public class MgInt32BinaryOperatorDivide extends MgInt32BinaryOperator {
    private static MgInt32BinaryOperatorDivide instance;

    public static MgInt32BinaryOperatorDivide getInstance() {
        if(instance == null) instance = new MgInt32BinaryOperatorDivide();
        return instance;
    }

    private MgInt32BinaryOperatorDivide() {
        setName(new Text("/"));
    }


    @Override
    protected int compute(int left, int right) {
        return left / right;
    }
}
