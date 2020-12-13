package cz.mg.application.entities.dynamical;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.MgProcedure;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.array.ReadableArray;


public class MgType extends MgEntity {
    @Mandatory @Link
    private final ReadableArray<MgType> types;

    @Mandatory @Link
    private final ReadableArray<MgVariable> variables;

    @Mandatory @Link
    private final ReadableArray<MgProcedure> procedures;

    @Mandatory @Link
    private final ReadableArray<MgInterface> interfaces;

    public MgType(
        ReadableArray<MgType> types,
        ReadableArray<MgVariable> variables,
        ReadableArray<MgProcedure> procedures,
        ReadableArray<MgInterface> interfaces
    ) {
        this.types = types;
        this.variables = variables;
        this.procedures = procedures;
        this.interfaces = interfaces;
    }

    public ReadableArray<MgType> getTypes() {
        return types;
    }

    public ReadableArray<MgVariable> getVariables() {
        return variables;
    }

    public ReadableArray<MgProcedure> getProcedures() {
        return procedures;
    }

    public ReadableArray<MgInterface> getInterfaces() {
        return interfaces;
    }
}
