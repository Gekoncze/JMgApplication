package cz.mg.application.entities.dynamical.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Parent;
import cz.mg.application.entities.dynamical.objects.MgClassObject;
import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.components.definitions.MgOperator;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.map.Map;
import cz.mg.collections.map.ReadableMap;


public class MgClassType extends MgStructuredType {
    @Mandatory @Parent
    private final MgClass clazz;

    @Mandatory @Link
    private final ReadableArray<MgProcedure> procedures;

    @Mandatory @Link
    private final ReadableArray<MgInterface> interfaces;

    @Mandatory @Link
    private final ReadableArray<MgOperator> operators;

    @Mandatory @Link
    private final ReadableMap<MgInterface, MgProcedure> procedureMap;

    public MgClassType(
        MgClass clazz,
        ReadableArray<MgType> types,
        ReadableArray<MgVariable> variables,
        ReadableArray<MgProcedure> procedures,
        ReadableArray<MgInterface> interfaces,
        ReadableArray<MgOperator> operators,
        Map<MgInterface, MgProcedure> procedureMap
    ) {
        super(types, variables);
        this.clazz = clazz;
        this.procedures = procedures;
        this.interfaces = interfaces;
        this.operators = operators;
        this.procedureMap = procedureMap;
    }

    public MgClass getClazz() {
        return clazz;
    }

    public ReadableArray<MgProcedure> getProcedures() {
        return procedures;
    }

    public ReadableArray<MgInterface> getInterfaces() {
        return interfaces;
    }

    public ReadableArray<MgOperator> getOperators() {
        return operators;
    }

    public ReadableMap<MgInterface, MgProcedure> getProcedureMap() {
        return procedureMap;
    }

    public MgProcedure getProcedure(MgInterface mgInterface){
        return procedureMap.get(mgInterface);
    }

    @Override
    public MgClassObject create(){
        return new MgClassObject(this);
    }
}
