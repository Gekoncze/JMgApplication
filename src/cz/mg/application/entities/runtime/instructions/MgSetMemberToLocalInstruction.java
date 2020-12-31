package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgStructuredObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public class MgSetMemberToLocalInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgVariable source;

    @Mandatory @Link
    private final MgVariable sourceMember;

    @Mandatory @Link
    private final MgVariable destination;

    public MgSetMemberToLocalInstruction(
        MgCommand command,
        MgVariable source,
        MgVariable sourceMember,
        MgVariable destination
    ) {
        super(command);
        this.source = source;
        this.sourceMember = sourceMember;
        this.destination = destination;
    }

    @Override
    public void run(MgTask task) {
        MgStructuredObject sourceObject = (MgStructuredObject) task.getObject(source);
        task.setObject(destination, sourceObject.getObject(sourceMember));
        super.run(task);
    }
}
