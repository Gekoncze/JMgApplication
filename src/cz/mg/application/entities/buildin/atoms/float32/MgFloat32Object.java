package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.dynamical.objects.MgAtomicObject;


public class MgFloat32Object extends MgAtomicObject {
    @Mandatory @Value
    private int value;

    public MgFloat32Object() {
        super(MgFloat32.getInstance().getType());
    }

    public MgFloat32Object(int value) {
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