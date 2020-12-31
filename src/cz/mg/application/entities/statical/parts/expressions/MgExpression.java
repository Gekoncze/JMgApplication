package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.application.entities.runtime.MgRuntimeExpression;
import cz.mg.application.entities.statical.parts.MgPart;


public abstract class MgExpression extends MgPart {
    @Optional @Cache
    private MgRuntimeExpression runtimeExpression;

    public MgExpression() {
    }

    public MgRuntimeExpression getRuntimeExpression() {
        return runtimeExpression;
    }

    public void setRuntimeExpression(MgRuntimeExpression runtimeExpression) {
        this.runtimeExpression = runtimeExpression;
    }
}
