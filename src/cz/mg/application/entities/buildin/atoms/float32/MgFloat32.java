package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.application.entities.objects.MgAtomicObject;
import cz.mg.application.entities.components.definitions.MgAtom;
import cz.mg.collections.text.Text;


public class MgFloat32 extends MgAtom {
    private static MgFloat32 instance;

    public static MgFloat32 getInstance() {
        if(instance == null) instance = new MgFloat32();
        return instance;
    }

    private MgFloat32() {
        setName(new Text("Float32"));
    }

    public MgFloat32 init(){
        getOperators().addLast(MgFloat32BinaryOperatorPlus.getInstance());
        getOperators().addLast(MgFloat32BinaryOperatorMinus.getInstance());
        getOperators().addLast(MgFloat32BinaryOperatorMultiply.getInstance());
        getOperators().addLast(MgFloat32BinaryOperatorDivide.getInstance());
        getOperators().addLast(MgFloat32LunaryOperatorPlus.getInstance());
        getOperators().addLast(MgFloat32LunaryOperatorMinus.getInstance());
        return this;
    }

    @Override
    public MgAtomicObject create() {
        return new MgFloat32Object();
    }

    @Override
    public Text toText(MgAtomicObject atom) {
        if(atom == null) return null;
        return new Text(
            Float.toString(
                ((MgFloat32Object)atom).getValue()
            )
        );
    }

    @Override
    public MgAtomicObject fromText(Text text) {
        if(text == null) return null;
        return new MgFloat32Object(
            Float.parseFloat(
                text.toString()
            )
        );
    }
}
