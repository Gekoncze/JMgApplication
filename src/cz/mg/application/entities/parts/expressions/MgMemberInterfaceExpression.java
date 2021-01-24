package cz.mg.application.entities.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.parts.MgInterface;
import cz.mg.collections.list.List;


public class MgMemberInterfaceExpression extends MgMemberExpression {
    @Optional @Link
    private MgInterface mgInterface;

    @Mandatory @Part
    private final List<MgExpression> input = new List<>();

    public MgMemberInterfaceExpression() {
    }

    public MgInterface getInterface() {
        return mgInterface;
    }

    public void setInterface(MgInterface mgInterface) {
        this.mgInterface = mgInterface;
    }

    public List<MgExpression> getInput() {
        return input;
    }
}
