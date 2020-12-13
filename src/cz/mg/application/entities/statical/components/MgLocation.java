package cz.mg.application.entities.statical.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.MgComponent;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public class MgLocation extends MgComponent {
    @Mandatory @Part
    private final List<MgComponent> components = new List<>();

    public MgLocation(Text name) {
        super(name);
    }

    public List<MgComponent> getComponents() {
        return components;
    }
}
