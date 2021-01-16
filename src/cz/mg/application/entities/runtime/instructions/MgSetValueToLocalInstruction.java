package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgAtomicObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;


public class MgSetValueToLocalInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgAtomicObject value;

    @Mandatory @Link
    private final MgInstanceVariable destination;

    public MgSetValueToLocalInstruction(
        MgAtomicObject value,
        MgInstanceVariable destination
    ) {
        this.value = value;
        this.destination = destination;
    }

    public MgAtomicObject getValue() {
        return value;
    }

    public MgInstanceVariable getDestination() {
        return destination;
    }

    @Override
    public void run(MgTask task) {
        task.setObject(destination, value);
        super.run(task);
    }
}
