package cz.mg.application.entities.dynamical.objects;

import cz.mg.application.entities.dynamical.MgObject;
import cz.mg.application.entities.dynamical.types.MgAtomicType;


public abstract class MgAtomicObject extends MgObject {
    public MgAtomicObject(MgAtomicType type) {
        super(type);
    }

    @Override
    public MgAtomicType getType() {
        return (MgAtomicType) super.getType();
    }
}
