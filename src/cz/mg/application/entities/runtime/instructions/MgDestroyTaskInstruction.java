package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.Connection;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.runtime.types.MgProcedureType;
import cz.mg.collections.array.ReadableArray;


public class MgDestroyTaskInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgProcedureType procedureType;

    @Mandatory @Part
    private final ReadableArray<Connection> parameters;

    public MgDestroyTaskInstruction(MgProcedureType procedureType, ReadableArray<Connection> parameters) {
        this.procedureType = procedureType;
        this.parameters = parameters;
    }

    @Override
    public void run(MgTask task) {
        MgThread thread = MgThread.getInstance();
        thread.getStack().removeLast();
        MgTask parentTask = thread.getStack().getLast();
        for(Connection parameter : parameters){
            parameter.run(parentTask, task);
        }
        super.run(task);
    }
}
