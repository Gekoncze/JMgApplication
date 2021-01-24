package cz.mg.application.entities.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.Named;
import cz.mg.application.entities.parts.variables.MgInterfaceVariable;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public class MgInterface extends MgPart implements Named {
    @Mandatory @Value
    private Text name = new Text();

    @Mandatory @Part
    private final List<MgInterfaceVariable> input = new List<>();

    @Mandatory @Part
    private final List<MgInterfaceVariable> output = new List<>();

    public MgInterface() {
    }

    @Override
    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public List<MgInterfaceVariable> getInput() {
        return input;
    }

    public List<MgInterfaceVariable> getOutput() {
        return output;
    }
}
