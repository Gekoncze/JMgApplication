package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.statical.MgStaticalEntity;


public class MgSequence extends MgStaticalEntity {
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
