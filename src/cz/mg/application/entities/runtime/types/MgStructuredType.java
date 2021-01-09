package cz.mg.application.entities.runtime.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgStructuredObject;
import cz.mg.application.entities.statical.parts.variables.MgVariable;
import cz.mg.collections.array.ReadonlyArray;


public abstract class MgStructuredType extends MgType {
    @Mandatory @Link
    private final ReadonlyArray<MgVariable> variables;

    public MgStructuredType(ReadonlyArray<MgType> types, ReadonlyArray<MgVariable> variables) {
        super(types);
        this.variables = variables;
    }

    public ReadonlyArray<MgVariable> getVariables() {
        return variables;
    }

    @Override
    public abstract MgStructuredObject create();
}
