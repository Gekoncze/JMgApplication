//package cz.mg.application.entities.statical.components.definitions.atoms.int32;
//
//import cz.mg.application.entities.dynamical.objects.MgAtomicObject;
//import cz.mg.application.entities.statical.components.definitions.MgAtom;
//import cz.mg.collections.text.Text;
//
//
//public class MgInt32 extends MgAtom {
//    private static MgInt32 instance;
//
//    public static MgInt32 getInstance() {
//        if(instance == null) instance = new MgInt32();
//        return instance;
//    }
//
//    private MgInt32() {
//        super(new Text("Int32"));
//        getProcedures().addLast(MgInt32BinaryOperatorPlus.getinstance());
//    }
//
//    @Override
//    public MgAtomicObject create() {
//        return new MgInt32Object();
//    }
//}
