package cz.mg.application.entities.statical.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.statical.parts.MgIdentity;
import cz.mg.collections.map.Map;


public class MgModule extends MgLocation {
    @Mandatory @Value
    private final MgIdentity identity;

    @Mandatory @Cache
    private final Map<Long, Long> moduleToAppIdMap = new Map<>();

    public MgModule(MgIdentity identity) {
        this.identity = identity;
    }

    public MgIdentity getIdentity() {
        return identity;
    }

    public Map<Long, Long> getModuleToAppIdMap() {
        return moduleToAppIdMap;
    }
}
