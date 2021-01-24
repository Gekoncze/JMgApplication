package cz.mg.application.entities.parts.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.objects.MgStructuredObject;
import cz.mg.application.entities.objects.MgTask;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;


public class MgSetLocalToMemberInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgInstanceVariable source;

    @Mandatory @Link
    private final MgInstanceVariable destination;

    @Mandatory @Link
    private final MgInstanceVariable destinationMember;

    public MgSetLocalToMemberInstruction(
        MgInstanceVariable source,
        MgInstanceVariable destination,
        MgInstanceVariable destinationMember
    ) {
        this.source = source;
        this.destination = destination;
        this.destinationMember = destinationMember;
    }

    public MgInstanceVariable getSource() {
        return source;
    }

    public MgInstanceVariable getDestination() {
        return destination;
    }

    public MgInstanceVariable getDestinationMember() {
        return destinationMember;
    }

    @Override
    public void run(MgTask task) {
        MgStructuredObject destinationObject = (MgStructuredObject) task.getObject(destination);
        destinationObject.setObject(destinationMember, task.getObject(source));
        super.run(task);
    }
}
