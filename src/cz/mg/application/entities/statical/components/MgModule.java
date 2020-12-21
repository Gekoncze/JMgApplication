package cz.mg.application.entities.statical.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.statical.parts.MgIdentity;


public class MgModule extends MgLocation {
    @Mandatory @Value
    private final MgIdentity identity;

    public MgModule(MgIdentity identity) {
        this.identity = identity;
    }

    public MgIdentity getIdentity() {
        return identity;
    }
}
