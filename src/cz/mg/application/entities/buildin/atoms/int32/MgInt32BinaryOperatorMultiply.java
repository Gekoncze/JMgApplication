package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.collections.text.Text;


public class MgInt32BinaryOperatorMultiply extends MgInt32BinaryOperator {
    private static MgInt32BinaryOperatorMultiply instance;

    public static MgInt32BinaryOperatorMultiply getInstance() {
        if(instance == null) instance = new MgInt32BinaryOperatorMultiply();
        return instance;
    }

    private MgInt32BinaryOperatorMultiply() {
        setName(new Text("*"));
    }


    @Override
    protected int compute(int left, int right) {
        return left * right;
    }
}
