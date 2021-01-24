package cz.mg.application.entities.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.objects.MgStructuredObject;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.collections.array.ReadonlyArray;


public abstract class MgStructuredType extends MgType {
    @Mandatory @Link
    private final ReadonlyArray<MgInstanceVariable> instanceVariables;

    public MgStructuredType(ReadonlyArray<MgType> types, ReadonlyArray<MgInstanceVariable> instanceVariables) {
        super(types);
        this.instanceVariables = instanceVariables;
    }

    public ReadonlyArray<MgInstanceVariable> getInstanceVariables() {
        return instanceVariables;
    }

    @Override
    public abstract MgStructuredObject create();
}
