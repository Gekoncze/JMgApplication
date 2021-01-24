package cz.mg.application.entities;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;


public abstract class MgEntity {
    @Mandatory @Value
    private long id = -1;

    public MgEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
