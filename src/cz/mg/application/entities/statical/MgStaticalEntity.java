package cz.mg.application.entities.statical;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.MgEntity;


public abstract class MgStaticalEntity extends MgEntity {
    @Optional @Value
    private Long id;

    public MgStaticalEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
