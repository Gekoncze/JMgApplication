package cz.mg.application.entities.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.objects.MgObject;
import cz.mg.collections.array.ReadonlyArray;


public abstract class MgType extends MgEntity {
    @Mandatory @Link
    private final ReadonlyArray<MgType> types;

    public MgType(ReadonlyArray<MgType> types) {
        this.types = types;
    }

    public ReadonlyArray<MgType> getTypes() {
        return types;
    }

    public abstract MgObject create();

    public boolean is(MgType type){
        return this == type || types.contains(type);
    }

    public boolean maybe(MgType type){
        return this == type || types.contains(type) || type.types.contains(this);
    }
}
