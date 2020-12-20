package cz.mg.application.entities.dynamical;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.MgProcedure;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.map.Map;


public abstract class MgObject extends MgEntity {
    @Mandatory @Link
    private final MgType type;

    @Mandatory @Shared
    private final Map<MgVariable, MgObject> objects = new Map<>();

    @Mandatory @Shared
    private final Map<MgInterface, MgProcedure> procedures;

    public MgObject(MgType type, Map<MgInterface, MgProcedure> procedures) {
        this.type = type;
        this.procedures = procedures;
    }

    public MgType getType() {
        return type;
    }

    public Map<MgVariable, MgObject> getObjects() {
        return objects;
    }

    public Map<MgInterface, MgProcedure> getProcedures() {
        return procedures;
    }

    public MgObject getObject(MgVariable variable){
        return objects.get(variable);
    }

    public MgProcedure getProcedure(MgInterface mgInterface){
        return procedures.get(mgInterface);
    }
}
