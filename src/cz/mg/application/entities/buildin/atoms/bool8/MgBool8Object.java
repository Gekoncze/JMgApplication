package cz.mg.application.entities.buildin.atoms.bool8;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.objects.MgAtomicObject;


public class MgBool8Object extends MgAtomicObject {
    @Mandatory @Value
    private boolean value;

    public MgBool8Object() {
        super(MgBool8.getInstance().getType());
    }

    public MgBool8Object(boolean value) {
        this();
        setValue(value);
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}