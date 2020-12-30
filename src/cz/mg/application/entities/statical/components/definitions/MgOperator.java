package cz.mg.application.entities.statical.components.definitions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.application.entities.runtime.types.MgProcedureType;
import cz.mg.application.entities.statical.components.MgDefinition;


public abstract class MgOperator extends MgDefinition {
    @Optional @Cache
    private MgProcedureType type;

    public MgOperator() {
    }

    @Override
    public MgProcedureType getType() {
        return type;
    }

    public void setType(MgProcedureType type) {
        this.type = type;
    }
}
