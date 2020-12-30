package cz.mg.application.entities.runtime.objects;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.application.entities.runtime.types.MgStructuredType;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.map.Map;


public abstract class MgStructuredObject extends MgObject {
    @Mandatory @Shared
    private final Map<MgVariable, MgObject> objects = new Map<>();

    public MgStructuredObject(MgStructuredType type) {
        super(type);
    }

    @Override
    public MgStructuredType getType() {
        return (MgStructuredType) super.getType();
    }

    public MgObject getObject(MgVariable variable){
        return objects.get(variable);
    }

    public void setObject(MgVariable variable, MgObject object){
        objects.set(variable, object);
    }
}
