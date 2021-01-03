package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.collections.text.Text;


public class MgFloat32BinaryOperatorMinus extends MgFloat32BinaryOperator {
    private static MgFloat32BinaryOperatorMinus instance;

    public static MgFloat32BinaryOperatorMinus getInstance() {
        if(instance == null) instance = new MgFloat32BinaryOperatorMinus();
        return instance;
    }

    private MgFloat32BinaryOperatorMinus() {
        setName(new Text("-"));
    }


    @Override
    protected float compute(float left, float right) {
        return left - right;
    }
}
