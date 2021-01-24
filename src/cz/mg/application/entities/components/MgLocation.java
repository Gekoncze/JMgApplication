package cz.mg.application.entities.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public class MgLocation extends MgComponent {
    @Mandatory @Part
    private final List<MgComponent> components = new List<>();

    public MgLocation() {
    }

    public List<MgComponent> getComponents() {
        return components;
    }
}
