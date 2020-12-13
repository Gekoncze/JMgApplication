package cz.mg.application.entities.statical.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.statical.parts.MgIdentity;
import cz.mg.collections.text.Text;


public class MgModule extends MgLocation {
    @Mandatory @Value
    private final MgIdentity identity;

    public MgModule(Text name, MgIdentity identity) {
        super(name);
        this.identity = identity;
    }

    public MgIdentity getIdentity() {
        return identity;
    }
}
