package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgStructuredObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;


public class MgMemberVariableInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgInstanceVariable parent;

    @Mandatory @Link
    private final MgInstanceVariable child;

    @Mandatory @Link
    private final MgInstanceVariable output;

    public MgMemberVariableInstruction(MgInstanceVariable parent, MgInstanceVariable child, MgInstanceVariable output) {
        this.parent = parent;
        this.child = child;
        this.output = output;
    }

    public MgInstanceVariable getParent() {
        return parent;
    }

    public MgInstanceVariable getChild() {
        return child;
    }

    public MgInstanceVariable getOutput() {
        return output;
    }

    @Override
    public void run(MgTask task) {
        MgStructuredObject parentObject = (MgStructuredObject) task.getObject(parent);
        task.setObject(output, parentObject.getObject(child));
        super.run(task);
    }
}
