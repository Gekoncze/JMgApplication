package cz.mg.application.entities.runtime;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;
import cz.mg.collections.array.ReadableArray;


public class MgRuntimeExpression extends MgRuntimeEntity {
    @Mandatory @Link
    private final MgExpression expression;

    @Mandatory @Part
    private final ReadableArray<MgVariable> variables;

    public MgRuntimeExpression(MgExpression expression, ReadableArray<MgVariable> variables) {
        this.expression = expression;
        this.variables = variables;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public ReadableArray<MgVariable> getVariables() {
        return variables;
    }
}
