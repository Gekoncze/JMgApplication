package cz.mg.application.entities.statical.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.Named;
import cz.mg.application.entities.statical.MgStaticalEntity;
import cz.mg.collections.text.Text;


public abstract class MgComponent extends MgStaticalEntity implements Named {
    @Mandatory @Value
    private Text name = new Text();

    public MgComponent() {
    }

    @Override
    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }
}
