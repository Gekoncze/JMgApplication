package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.statical.MgStaticalEntity;
import cz.mg.application.entities.statical.parts.MgIdentity;
import cz.mg.collections.map.Map;


public class MgDependency extends MgStaticalEntity {
    @Mandatory @Value
    private final MgIdentity identity;

    @Mandatory @Part
    private final Map<Long, Long> idMap = new Map<>();

    public MgDependency(MgIdentity identity) {
        this.identity = identity;
    }

    public MgIdentity getIdentity() {
        return identity;
    }

    public Map<Long, Long> getIdMap() {
        return idMap;
    }
}
