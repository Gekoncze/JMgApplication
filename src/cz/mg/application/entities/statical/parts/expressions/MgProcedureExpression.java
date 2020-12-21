package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.collections.list.List;


public class MgProcedureExpression extends MgExpression {
    @Optional @Link
    private MgProcedure procedure;

    @Mandatory @Part
    private final List<MgExpression> expressions = new List<>();

    public MgProcedureExpression() {
    }

    public MgProcedure getProcedure() {
        return procedure;
    }

    public void setProcedure(MgProcedure procedure) {
        this.procedure = procedure;
    }

    public List<MgExpression> getExpressions() {
        return expressions;
    }
}
