package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.collections.list.List;


public class MgMemberProcedureExpression extends MgMemberExpression {
    @Optional @Link
    private MgProcedure procedure;

    @Mandatory @Part
    private final List<MgExpression> input = new List<>();

    public MgMemberProcedureExpression() {
    }

    public MgProcedure getProcedure() {
        return procedure;
    }

    public void setProcedure(MgProcedure procedure) {
        this.procedure = procedure;
    }

    public List<MgExpression> getInput() {
        return input;
    }
}
