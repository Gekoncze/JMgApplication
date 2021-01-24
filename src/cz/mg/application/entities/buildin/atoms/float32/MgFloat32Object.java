package cz.mg.application.entities.buildin.atoms.float32;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.objects.MgAtomicObject;


public class MgFloat32Object extends MgAtomicObject {
    @Mandatory @Value
    private float value;

    public MgFloat32Object() {
        super(MgFloat32.getInstance().getType());
    }

    public MgFloat32Object(float value) {
        this();
        setValue(value);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}