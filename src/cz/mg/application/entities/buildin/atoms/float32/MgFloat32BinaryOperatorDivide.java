package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.collections.text.Text;


public class MgFloat32BinaryOperatorDivide extends MgFloat32BinaryOperator {
    private static MgFloat32BinaryOperatorDivide instance;

    public static MgFloat32BinaryOperatorDivide getInstance() {
        if(instance == null) instance = new MgFloat32BinaryOperatorDivide();
        return instance;
    }

    private MgFloat32BinaryOperatorDivide() {
        setName(new Text("/"));
    }


    @Override
    protected float compute(float left, float right) {
        return left / right;
    }
}
