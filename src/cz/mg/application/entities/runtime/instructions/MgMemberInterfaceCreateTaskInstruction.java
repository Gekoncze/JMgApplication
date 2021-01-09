package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.Connection;
import cz.mg.application.entities.runtime.objects.MgClassObject;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.variables.MgVariable;
import cz.mg.collections.array.ReadableArray;


public class MgMemberInterfaceCreateTaskInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgVariable parent;

    @Mandatory @Link
    private final MgInterface mgInterface;

    @Mandatory @Link
    private final ReadableArray<Connection> parameters;

    public MgMemberInterfaceCreateTaskInstruction(
        MgVariable parent,
        MgInterface mgInterface,
        ReadableArray<Connection> parameters
    ) {
        this.parent = parent;
        this.mgInterface = mgInterface;
        this.parameters = parameters;
    }

    @Override
    public void run(MgTask task) {
        MgClassObject parentObject = (MgClassObject) task.getObject(parent);
        MgProcedure procedure = parentObject.getType().getProcedure(mgInterface);
        MgTask newTask = procedure.getType().create();
        for(Connection parameter : parameters){
            parameter.run(task, newTask);
        }
        MgThread.getInstance().getStack().addLast(newTask);
        super.run(task);
    }
}
