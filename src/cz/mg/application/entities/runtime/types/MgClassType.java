package cz.mg.application.entities.runtime.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Parent;
import cz.mg.application.entities.runtime.objects.MgClassObject;
import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.components.definitions.MgOperator;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.map.Map;
import cz.mg.collections.map.ReadableMap;


public class MgClassType extends MgStructuredType {
    @Mandatory @Parent
    private final MgClass clazz;

    @Mandatory @Link
    private final ReadonlyArray<MgProcedure> procedures;

    @Mandatory @Link
    private final ReadonlyArray<MgInterface> interfaces;

    @Mandatory @Link
    private final ReadonlyArray<MgOperator> operators;

    @Mandatory @Link
    private final ReadableMap<MgInterface, MgProcedure> procedureMap;

    public MgClassType(
        MgClass clazz,
        ReadonlyArray<MgType> types,
        ReadonlyArray<MgInstanceVariable> instanceVariables,
        ReadonlyArray<MgProcedure> procedures,
        ReadonlyArray<MgInterface> interfaces,
        ReadonlyArray<MgOperator> operators,
        Map<MgInterface, MgProcedure> procedureMap
    ) {
        super(types, instanceVariables);
        this.clazz = clazz;
        this.procedures = procedures;
        this.interfaces = interfaces;
        this.operators = operators;
        this.procedureMap = procedureMap;
    }

    public MgClass getClazz() {
        return clazz;
    }

    public ReadonlyArray<MgProcedure> getProcedures() {
        return procedures;
    }

    public ReadonlyArray<MgInterface> getInterfaces() {
        return interfaces;
    }

    public ReadonlyArray<MgOperator> getOperators() {
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
