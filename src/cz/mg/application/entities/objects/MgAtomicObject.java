package cz.mg.application.entities.objects;

import cz.mg.application.entities.types.MgAtomicType;


public abstract class MgAtomicObject extends MgObject {
    public MgAtomicObject(MgAtomicType type) {
        super(type);
    }

    @Override
    public MgAtomicType getType() {
        return (MgAtomicType) super.getType();
    }
}
