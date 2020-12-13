package cz.mg.application.entities.statical.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.Named;
import cz.mg.application.entities.statical.MgPart;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public class MgInterface extends MgPart implements Named {
    @Mandatory @Value
    private final Text name;

    @Mandatory @Part
    private final List<MgVariable> input = new List<>();

    @Mandatory @Part
    private final List<MgVariable> output = new List<>();

    public MgInterface(Text name) {
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
}
