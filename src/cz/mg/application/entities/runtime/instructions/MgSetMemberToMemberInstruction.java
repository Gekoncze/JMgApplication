package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgStructuredObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public class MgSetMemberToMemberInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgVariable source;

    @Mandatory @Link
    private final MgVariable sourceMember;

    @Mandatory @Link
    private final MgVariable destination;

    @Mandatory @Link
    private final MgVariable destinationMember;

    public MgSetMemberToMemberInstruction(
        MgCommand command,
        MgVariable source,
        MgVariable sourceMember,
        MgVariable destination,
        MgVariable destinationMember
    ) {
        super(command);
        this.source = source;
        this.sourceMember = sourceMember;
        this.destination = destination;
        this.destinationMember = destinationMember;
    }

    @Override
    public void run(MgTask task) {
        MgStructuredObject destinationObject = (MgStructuredObject) task.getObject(destination);
        MgStructuredObject sourceObject = (MgStructuredObject) task.getObject(source);
        destinationObject.setObject(destinationMember, sourceObject.getObject(sourceMember));
        super.run(task);
    }
}
