package cz.mg.application.entities.buildin.atoms.int32;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.objects.MgAtomicObject;


public class MgInt32Object extends MgAtomicObject {
    @Mandatory @Value
    private int value;

    public MgInt32Object() {
        super(MgInt32.getInstance().getType());
    }

    public MgInt32Object(int value) {
        this();
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}