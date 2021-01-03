package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.application.entities.runtime.objects.MgAtomicObject;
import cz.mg.application.entities.statical.components.definitions.MgAtom;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.text.Text;


public class MgInt32 extends MgAtom {
    private static MgInt32 instance;

    public static MgInt32 getInstance() {
        if(instance == null) instance = new MgInt32();
        return instance;
    }

    private MgInt32() {
        setName(new Text("Int32"));
    }

    public MgInt32 init(){
        getOperators().addLast(MgInt32BinaryOperatorPlus.getInstance());
        getOperators().addLast(MgInt32BinaryOperatorMinus.getInstance());
        getOperators().addLast(MgInt32BinaryOperatorMultiply.getInstance());
        getOperators().addLast(MgInt32BinaryOperatorDivide.getInstance());
        getOperators().addLast(MgInt32BinaryOperatorModulo.getInstance());
        getOperators().addLast(MgInt32LunaryOperatorPlus.getInstance());
        getOperators().addLast(MgInt32LunaryOperatorMinus.getInstance());
        return this;
    }

    @Override
    public MgAtomicObject create() {
        return new MgInt32Object();
    }

    @Override
    public Text toText(MgAtomicObject atom) {
        if(atom == null) return null;
        return new Text(
            Integer.toString(
                ((MgInt32Object)atom).getValue()
            )
        );
    }

    @Override
    public MgAtomicObject fromText(Text text) {
        if(text == null) return null;
        return new MgInt32Object(
            Integer.parseInt(
                text.toString()
            )
        );
    }
}
