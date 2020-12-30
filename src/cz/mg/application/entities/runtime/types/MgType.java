package cz.mg.application.entities.runtime.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.runtime.objects.MgObject;
import cz.mg.collections.array.ReadableArray;


public abstract class MgType extends MgEntity {
    @Mandatory @Link
    private final ReadableArray<MgType> types;

    public MgType(ReadableArray<MgType> types) {
        this.types = types;
    }

    public ReadableArray<MgType> getTypes() {
        return types;
    }

    public abstract MgObject create();

    public boolean is(MgType type){
        return this == type || types.contains(type);
    }

    public boolean maybe(MgType type){
        return this == type || type.types.contains(this);
    }
}
