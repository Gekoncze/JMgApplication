package cz.mg.application.entities;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;


public abstract class MgEntity {
    public static final long NULL_ID = -1;

    @Mandatory @Value
    private long id = NULL_ID;

    public MgEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
