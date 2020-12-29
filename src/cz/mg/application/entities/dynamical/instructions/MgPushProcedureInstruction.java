package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.dynamical.Connection;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.collections.array.ReadableArray;


public class MgPushProcedureInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgProcedure procedure;

    @Mandatory @Part
    private final ReadableArray<Connection> parameters;

    public MgPushProcedureInstruction(MgProcedure procedure, ReadableArray<Connection> parameters) {
        this.procedure = procedure;
        this.parameters = parameters;
    }

    @Override
    public void run(MgTask task) {
        super.run(task);
        MgTask newTask = procedure.getType().create();
        for(Connection parameter : parameters){
            parameter.run(task, newTask);
        }
        task.getThread().getStack().addLast(newTask);
    }
}
