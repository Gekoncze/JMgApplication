//package cz.mg.application.entities.statical.components.definitions.atoms.int32;
//
//import cz.mg.application.entities.dynamical.objects.MgTask;
//import cz.mg.application.entities.statical.parts.MgVariable;
//import cz.mg.collections.text.Text;
//
//
//public abstract class MgInt32RunaryOperator extends MgInt32Operator {
//    private final MgVariable leftVariable = new MgVariable(MgInt32.getInstance(), new Text("a"));
//    private final MgVariable resultVariable = new MgVariable(MgInt32.getInstance(), new Text("r"));
//
//    public MgInt32RunaryOperator(Text name) {
//        super(name);
//        getInput().addLast(leftVariable);
//        getOutput().addLast(resultVariable);
//    }
//
//    @Override
//    public void run(MgTask task) {
//        MgInt32Object leftObject = (MgInt32Object) task.getObject(this.leftVariable);
//        int left = leftObject.getValue();
//        int result = compute(left);
//        task.setObject(this.resultVariable, new MgInt32Object(result));
//    }
//
//    protected abstract int compute(int left);
//}
