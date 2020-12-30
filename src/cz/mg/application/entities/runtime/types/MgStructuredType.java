package cz.mg.application.entities.runtime.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgStructuredObject;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.array.ReadableArray;


public abstract class MgStructuredType extends MgType {
    @Mandatory @Link
    private final ReadableArray<MgVariable> variables;

    public MgStructuredType(ReadableArray<MgType> types, ReadableArray<MgVariable> variables) {
        super(types);
        this.variables = variables;
    }

    public ReadableArray<MgVariable> getVariables() {
        return variables;
    }

    @Override
    public abstract MgStructuredObject create();
}
