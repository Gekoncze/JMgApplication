package cz.mg.application.entities.runtime.objects;

import cz.mg.application.entities.runtime.types.MgClassType;


public class MgClassObject extends MgStructuredObject {
    public MgClassObject(MgClassType type) {
        super(type);
    }

    @Override
    public MgClassType getType() {
        return (MgClassType) super.getType();
    }
}
