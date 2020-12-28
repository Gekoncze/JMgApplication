package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.collections.text.Text;


public class MgBool8BinaryOperatorAnd extends MgBool8BinaryOperator {
    private static MgBool8BinaryOperatorAnd instance;

    public static MgBool8BinaryOperatorAnd getInstance() {
        if(instance == null) instance = new MgBool8BinaryOperatorAnd();
        return instance;
    }

    private MgBool8BinaryOperatorAnd() {
        setName(new Text("and"));
    }


    @Override
    protected boolean compute(boolean left, boolean right) {
        return left && right;
    }
}
