package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.Connection;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.collections.array.ReadableArray;


public class MgPushProcedureInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgProcedure procedure;

    @Mandatory @Part
    private final ReadableArray<Connection> parameters;

    public MgPushProcedureInstruction(
        MgCommand command,
        MgInstruction nextInstruction,
        MgProcedure procedure,
        ReadableArray<Connection> parameters
    ) {
        super(command, nextInstruction);
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
        MgThread.getInstance().getStack().addLast(newTask);
    }
}
