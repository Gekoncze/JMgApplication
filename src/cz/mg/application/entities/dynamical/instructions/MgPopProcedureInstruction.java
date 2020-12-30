package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.dynamical.Connection;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.collections.array.ReadableArray;


public class MgPopProcedureInstruction extends MgTerminatingInstruction {
    @Mandatory @Part
    private final ReadableArray<Connection> parameters;

    public MgPopProcedureInstruction(MgCommand command, ReadableArray<Connection> parameters) {
        super(command);
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
    }
}
