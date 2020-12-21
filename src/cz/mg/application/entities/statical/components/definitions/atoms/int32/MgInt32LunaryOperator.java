//package cz.mg.application.entities.statical.components.definitions.atoms.int32;
//
//import cz.mg.application.entities.dynamical.objects.MgTask;
//import cz.mg.application.entities.statical.parts.MgVariable;
//import cz.mg.collections.text.Text;
//
//
//public abstract class MgInt32LunaryOperator extends MgInt32Operator {
//    private final MgVariable rightVariable = new MgVariable(MgInt32.getInstance(), new Text("b"));
//    private final MgVariable resultVariable = new MgVariable(MgInt32.getInstance(), new Text("r"));
//
//    public MgInt32LunaryOperator(Text name) {
//        super(name);
//        getInput().addLast(rightVariable);
//        getOutput().addLast(resultVariable);
//    }
//
//    @Override
//    public void run(MgTask task) {
//        MgInt32Object rightObject = (MgInt32Object) task.getObject(this.rightVariable);
//        int right = rightObject.getValue();
//        int result = compute(right);
//        task.setObject(this.resultVariable, new MgInt32Object(result));
//    }
//
//    protected abstract int compute(int right);
//}
