package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgStructuredObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.MgVariable;


public class MgMemberVariableInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgVariable parent;

    @Mandatory @Link
    private final MgVariable child;

    @Mandatory @Link
    private final MgVariable output;

    public MgMemberVariableInstruction(MgVariable parent, MgVariable child, MgVariable output) {
        this.parent = parent;
        this.child = child;
        this.output = output;
    }

    @Override
    public void run(MgTask task) {
        MgStructuredObject parentObject = (MgStructuredObject) task.getObject(parent);
        task.setObject(output, parentObject.getObject(child));
        super.run(task);
    }
}
