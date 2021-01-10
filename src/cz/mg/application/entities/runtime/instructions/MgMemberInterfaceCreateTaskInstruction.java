package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.objects.MgClassObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.variables.MgVariable;
import cz.mg.collections.array.ReadonlyArray;


public class MgMemberInterfaceCreateTaskInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgVariable parent;

    @Mandatory @Link
    private final MgInterface mgInterface;

    @Mandatory @Link
    private final ReadonlyArray<MgInstanceVariable> sources;

    public MgMemberInterfaceCreateTaskInstruction(
        MgVariable parent,
        MgInterface mgInterface,
        ReadonlyArray<MgInstanceVariable> sources
    ) {
        this.parent = parent;
        this.mgInterface = mgInterface;
        this.sources = sources;
    }

    public MgVariable getParent() {
        return parent;
    }

    public MgInterface getInterface() {
        return mgInterface;
    }

    public ReadonlyArray<MgInstanceVariable> getSources() {
        return sources;
    }

    @Override
    public void run(MgTask task) {
        MgClassObject parentObject = (MgClassObject) task.getObject(parent);
        MgProcedure procedure = parentObject.getType().getProcedure(mgInterface);
        MgTask newTask = procedure.getType().create();
        int i = newTask.getType().getInputDelta();
        for(MgInstanceVariable source : sources){
            newTask.setObject(i, task.getObject(source));
            i++;
        }
        MgThread.getInstance().getStack().addLast(newTask);
        super.run(task);
    }
}
