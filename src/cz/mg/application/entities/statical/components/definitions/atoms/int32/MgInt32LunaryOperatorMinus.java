//package cz.mg.application.entities.statical.components.definitions.atoms.int32;
//
//import cz.mg.collections.text.Text;
//
//
//public class MgInt32LunaryOperatorMinus extends MgInt32LunaryOperator {
//    private static MgInt32LunaryOperatorMinus instance;
//
//    public static MgInt32LunaryOperatorMinus getInstance() {
//        if(instance == null) instance = new MgInt32LunaryOperatorMinus();
//        return instance;
//    }
//
//    private MgInt32LunaryOperatorMinus() {
//        super(new Text("-"));
//    }
//
//
//    @Override
//    protected int compute(int right) {
//        return -right;
//    }
//}
