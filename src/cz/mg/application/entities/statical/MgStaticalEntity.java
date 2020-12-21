package cz.mg.application.entities.statical;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.MgEntity;


public abstract class MgStaticalEntity extends MgEntity {
    @Mandatory @Value
    private int moduleId;

    @Mandatory @Value
    private int id;

    public MgStaticalEntity() {
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
