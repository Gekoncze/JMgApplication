package cz.mg.application.entities.statical.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.Named;
import cz.mg.application.entities.dynamical.types.MgProcedureType;
import cz.mg.application.entities.statical.MgPart;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public class MgProcedure extends MgPart implements Named {
    @Mandatory @Value
    private final Text name;

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

    public MgProcedure(Text name) {
        this.name = name;
    }

    @Override
    public Text getName() {
        return name;
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

    public MgProcedureType getType() {
        return type;
    }

    public void setType(MgProcedureType type) {
        this.type = type;
    }
}
