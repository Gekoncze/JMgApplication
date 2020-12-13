package cz.mg.application.entities.dynamical.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.dynamical.MgObject;
import cz.mg.application.entities.dynamical.MgType;
import cz.mg.application.entities.statical.components.MgClass;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.MgProcedure;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.map.Map;


public class MgClassType extends MgType {
    @Mandatory @Link
    private final MgClass clazz;

    @Mandatory @Link
    private final Map<MgInterface, MgProcedure> procedureMap;

    public MgClassType(
        MgClass clazz,
        ReadableArray<MgType> types,
        ReadableArray<MgVariable> variables,
        ReadableArray<MgProcedure> procedures,
        ReadableArray<MgInterface> interfaces,
        Map<MgInterface, MgProcedure> procedureMap
    ) {
        super(types, variables, procedures, interfaces);
        this.clazz = clazz;
        this.procedureMap = procedureMap;
    }

    public MgClass getClazz() {
        return clazz;
    }

    public Map<MgInterface, MgProcedure> getProcedureMap() {
        return procedureMap;
    }

    public MgProcedure getProcedure(MgInterface mgInterface){
        return procedureMap.get(mgInterface);
    }

    public MgObject create(){
        return new MgObject(this, procedureMap);
    }
}
