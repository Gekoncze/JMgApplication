//package cz.mg.application.entities.statical.components.definitions.atoms.int32;
//
//import cz.mg.collections.text.Text;
//
//
//public class MgInt32BinaryOperatorMinus extends MgInt32BinaryOperator {
//    private static MgInt32BinaryOperatorMinus instance;
//
//    public static MgInt32BinaryOperatorMinus getInstance() {
//        if(instance == null) instance = new MgInt32BinaryOperatorMinus();
//        return instance;
//    }
//
//    private MgInt32BinaryOperatorMinus() {
//        super(new Text("-"));
//    }
//
//
//    @Override
//    protected int compute(int left, int right) {
//        return left - right;
//    }
//}
