//package cz.mg.application.entities.statical.components.definitions.atoms.int32;
//
//import cz.mg.collections.text.Text;
//
//
//public class MgInt32BinaryOperatorPlus extends MgInt32BinaryOperator {
//    private static MgInt32BinaryOperatorPlus instance;
//
//    public static MgInt32BinaryOperatorPlus getInstance() {
//        if(instance == null) instance = new MgInt32BinaryOperatorPlus();
//        return instance;
//    }
//
//    private MgInt32BinaryOperatorPlus() {
//        super(new Text("+"));
//    }
//
//
//    @Override
//    protected int compute(int left, int right) {
//        return left + right;
//    }
//}
