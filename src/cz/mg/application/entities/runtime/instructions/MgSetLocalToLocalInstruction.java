package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;


public class MgSetLocalToLocalInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgInstanceVariable source;

    @Mandatory @Link
    private final MgInstanceVariable destination;

    public MgSetLocalToLocalInstruction(
        MgInstanceVariable source,
        MgInstanceVariable destination
    ) {
        this.source = source;
        this.destination = destination;
    }

    public MgInstanceVariable getSource() {
        return source;
    }

    public MgInstanceVariable getDestination() {
        return destination;
    }

    @Override
    public void run(MgTask task) {
        task.setObject(destination, task.getObject(source));
        super.run(task);
    }
}
