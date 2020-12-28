package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.collections.text.Text;


public class MgBool8BinaryOperatorOr extends MgBool8BinaryOperator {
    private static MgBool8BinaryOperatorOr instance;

    public static MgBool8BinaryOperatorOr getInstance() {
        if(instance == null) instance = new MgBool8BinaryOperatorOr();
        return instance;
    }

    private MgBool8BinaryOperatorOr() {
        setName(new Text("or"));
    }


    @Override
    protected boolean compute(boolean left, boolean right) {
        return left || right;
    }
}
