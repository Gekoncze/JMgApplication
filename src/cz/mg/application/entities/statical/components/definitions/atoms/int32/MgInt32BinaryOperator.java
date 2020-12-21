//package cz.mg.application.entities.statical.components.definitions.atoms.int32;
//
//import cz.mg.application.entities.dynamical.objects.MgTask;
//import cz.mg.application.entities.statical.parts.MgVariable;
//import cz.mg.collections.text.Text;
//
//
//public abstract class MgInt32BinaryOperator extends MgInt32Operator {
//    private final MgVariable leftVariable = new MgVariable(MgInt32.getInstance(), new Text("a"));
//    private final MgVariable rightVariable = new MgVariable(MgInt32.getInstance(), new Text("b"));
//    private final MgVariable resultVariable = new MgVariable(MgInt32.getInstance(), new Text("r"));
//
//    public MgInt32BinaryOperator(Text name) {
//        super(name);
//        getInput().addLast(leftVariable);
//        getInput().addLast(rightVariable);
//        getOutput().addLast(resultVariable);
//    }
//
//    @Override
//    public void run(MgTask task) {
//        MgInt32Object leftObject = (MgInt32Object) task.getObject(this.leftVariable);
//        MgInt32Object rightObject = (MgInt32Object) task.getObject(this.rightVariable);
//        int left = leftObject.getValue();
//        int right = rightObject.getValue();
//        int result = compute(left, right);
//        task.setObject(this.resultVariable, new MgInt32Object(result));
//    }
//
//    protected abstract int compute(int left, int right);
//}
