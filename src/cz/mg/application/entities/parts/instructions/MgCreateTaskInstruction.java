package cz.mg.application.entities.parts.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.architecture.MgThread;
import cz.mg.application.entities.objects.MgTask;
import cz.mg.application.entities.types.MgProcedureType;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.collections.array.ReadonlyArray;


public class MgCreateTaskInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgProcedureType procedureType;

    @Mandatory @Part
    private final ReadonlyArray<MgInstanceVariable> sources;

    public MgCreateTaskInstruction(MgProcedureType procedureType, ReadonlyArray<MgInstanceVariable> sources) {
        this.procedureType = procedureType;
        this.sources = sources;
    }

    public MgProcedureType getProcedureType() {
        return procedureType;
    }

    public ReadonlyArray<MgInstanceVariable> getSources() {
        return sources;
    }

    @Override
    public void run(MgTask task) {
        MgTask newTask = procedureType.create();
        int i = newTask.getType().getInputDelta();
        for(MgInstanceVariable source : sources){
            newTask.setObject(i, task.getObject(source));
            i++;
        }
        MgThread.getInstance().getStack().addLast(newTask);
        super.run(task);
    }
}
