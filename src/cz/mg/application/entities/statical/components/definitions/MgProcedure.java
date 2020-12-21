package cz.mg.application.entities.statical.components.definitions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.dynamical.types.MgProcedureType;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.list.List;


public class MgProcedure extends MgDefinition {
    @Mandatory @Part
    private final List<MgVariable> input = new List<>();

    @Mandatory @Part
    private final List<MgVariable> output = new List<>();

    @Optional @Part
    private final List<MgVariable> local = new List<>();

    @Optional @Link
    private MgInterface mgInterface;

    @Optional @Cache
    private MgProcedureType type;

    public MgProcedure() {
    }

    public List<MgVariable> getInput() {
        return input;
    }

    public List<MgVariable> getOutput() {
        return output;
    }

    public List<MgVariable> getLocal() {
        return local;
    }

    public MgInterface getInterface() {
        return mgInterface;
    }

    public void setInterface(MgInterface mgInterface) {
        this.mgInterface = mgInterface;
    }

    @Override
    public MgProcedureType getType() {
        return type;
    }

    public void setType(MgProcedureType type) {
        this.type = type;
    }
}
