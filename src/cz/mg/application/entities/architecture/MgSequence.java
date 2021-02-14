package cz.mg.application.entities.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.Store;
import cz.mg.application.entities.MgEntity;


@Store
public class MgSequence extends MgEntity {
    private static final long INITIAL_VALUE = 0;

    @Mandatory @Value
    private long value;

    public MgSequence() {
        setValue(INITIAL_VALUE);
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        if(value <= NULL_ID) throw new IllegalArgumentException("Sequence value must be > " + NULL_ID + ".");
        this.value = value;
    }

    public long next(){
        return value++;
    }
}
