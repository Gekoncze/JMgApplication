package cz.mg.application.entities.runtime.objects;

import cz.mg.application.entities.runtime.types.MgAtomicType;


public abstract class MgAtomicObject extends MgObject {
    public MgAtomicObject(MgAtomicType type) {
        super(type);
    }

    @Override
    public MgAtomicType getType() {
        return (MgAtomicType) super.getType();
    }
}
