package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.MgVariable;


public class MgSetLocalToLocalInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgVariable source;

    @Mandatory @Link
    private final MgVariable destination;

    public MgSetLocalToLocalInstruction(
        MgVariable source,
        MgVariable destination
    ) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void run(MgTask task) {
        task.setObject(destination, task.getObject(source));
        super.run(task);
    }
}
