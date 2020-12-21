package cz.mg.application.entities.statical.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.Named;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public class MgInterface extends MgPart implements Named {
    @Mandatory @Value
    private Text name = new Text();

    @Mandatory @Part
    private final List<MgVariable> input = new List<>();

    @Mandatory @Part
    private final List<MgVariable> output = new List<>();

    public MgInterface() {
    }

    @Override
    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public List<MgVariable> getInput() {
        return input;
    }

    public List<MgVariable> getOutput() {
        return output;
    }
}
