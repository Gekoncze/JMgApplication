package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.application.entities.runtime.objects.MgAtomicObject;
import cz.mg.application.entities.statical.components.definitions.MgAtom;
import cz.mg.collections.text.Text;


public class MgBool8 extends MgAtom {
    private static MgBool8 instance;

    public static MgBool8 getInstance() {
        if(instance == null) instance = new MgBool8();
        return instance;
    }

    private MgBool8() {
        setName(new Text("Bool8"));
    }

    public MgBool8 init(){
        getOperators().addLast(MgBool8BinaryOperatorAnd.getInstance());
        getOperators().addLast(MgBool8BinaryOperatorOr.getInstance());
        getOperators().addLast(MgBool8LunaryOperatorNot.getInstance());
        return this;
    }

    @Override
    public MgAtomicObject create() {
        return new MgBool8Object();
    }

    @Override
    public Text toText(MgAtomicObject atom) {
        if(atom == null) return null;
        MgBool8Object value = (MgBool8Object) atom;
        return new Text(
            value.getValue() ? "true" : "false"
        );
    }

    @Override
    public MgAtomicObject fromText(Text text) {
        if(text == null) return null;
        switch (text.toString()){
            case "true": return new MgBool8Object(true);
            case "false": return new MgBool8Object(false);
            default: throw new RuntimeException("Cannot convert '" + text.toString() + "' to boolean.");
        }
    }
}
