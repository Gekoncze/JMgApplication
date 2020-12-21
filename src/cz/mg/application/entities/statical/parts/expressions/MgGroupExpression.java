package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public class MgGroupExpression extends MgExpression {
    @Mandatory @Part
    private final List<MgExpression> expressions = new List<>();

    public MgGroupExpression() {
    }

    public List<MgExpression> getExpressions() {
        return expressions;
    }
}
