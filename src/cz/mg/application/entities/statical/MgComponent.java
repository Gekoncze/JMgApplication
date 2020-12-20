package cz.mg.application.entities.statical;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.MgEntity;
import cz.mg.application.Named;
import cz.mg.collections.text.Text;


public abstract class MgComponent extends MgEntity implements Named {
    @Mandatory @Value
    private final Text name;

    public MgComponent(Text name) {
        this.name = name;
    }

    @Override
    public Text getName() {
        return name;
    }
}
