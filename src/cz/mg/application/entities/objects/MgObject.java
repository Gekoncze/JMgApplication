package cz.mg.application.entities.objects;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.types.MgType;


public abstract class MgObject extends MgEntity {
    @Mandatory @Link
    private final MgType type;

    public MgObject(MgType type) {
        this.type = type;
    }

    public MgType getType() {
        return type;
    }
}
