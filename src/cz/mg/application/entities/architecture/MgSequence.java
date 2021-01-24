package cz.mg.application.entities.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.Store;
import cz.mg.application.entities.MgEntity;


@Store
public class MgSequence extends MgEntity {
    @Mandatory @Value
    private long value;

    public MgSequence() {
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long next(){
        return value++;
    }
}
