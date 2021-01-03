package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.collections.text.Text;


public class MgFloat32BinaryOperatorPlus extends MgFloat32BinaryOperator {
    private static MgFloat32BinaryOperatorPlus instance;

    public static MgFloat32BinaryOperatorPlus getInstance() {
        if(instance == null) instance = new MgFloat32BinaryOperatorPlus();
        return instance;
    }

    private MgFloat32BinaryOperatorPlus() {
        setName(new Text("+"));
    }


    @Override
    protected float compute(float left, float right) {
        return left + right;
    }
}
