package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.Connection;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.runtime.types.MgProcedureType;
import cz.mg.collections.array.ReadableArray;


public class MgCreateTaskInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgProcedureType procedureType;

    @Mandatory @Part
    private final ReadableArray<Connection> parameters;

    public MgCreateTaskInstruction(MgProcedureType procedureType, ReadableArray<Connection> parameters) {
        this.procedureType = procedureType;
        this.parameters = parameters;
    }

    @Override
    public void run(MgTask task) {
        MgTask newTask = procedureType.create();
        for(Connection parameter : parameters){
            parameter.run(task, newTask);
        }
        MgThread.getInstance().getStack().addLast(newTask);
        super.run(task);
    }
}
