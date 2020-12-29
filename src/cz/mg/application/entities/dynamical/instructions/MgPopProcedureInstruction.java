package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.dynamical.Connection;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.collections.array.ReadableArray;


public class MgPopProcedureInstruction extends MgLinearInstruction {
    @Mandatory @Part
    private final ReadableArray<Connection> parameters;

    public MgPopProcedureInstruction(ReadableArray<Connection> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void run(MgTask task) {
        super.run(task);
        task.getThread().getStack().removeLast();
        MgTask parentTask = task.getThread().getStack().getLast();
        for(Connection parameter : parameters){
            parameter.run(parentTask, task);
        }
    }
}
