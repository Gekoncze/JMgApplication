package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.collections.text.Text;


public class MgFloat32BinaryOperatorMultiply extends MgFloat32BinaryOperator {
    private static MgFloat32BinaryOperatorMultiply instance;

    public static MgFloat32BinaryOperatorMultiply getInstance() {
        if(instance == null) instance = new MgFloat32BinaryOperatorMultiply();
        return instance;
    }

    private MgFloat32BinaryOperatorMultiply() {
        setName(new Text("*"));
    }


    @Override
    protected int compute(int left, int right) {
        return left * right;
    }
}
