package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.runtime.types.MgProcedureType;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgValidator;
import cz.mg.collections.array.ReadonlyArray;


public class MgCreateTaskInstruction extends MgLinearInstruction {
    @Mandatory @Link
    private final MgProcedureType procedureType;

    @Mandatory @Part
    private final ReadonlyArray<MgInstanceVariable> sources;

    public MgCreateTaskInstruction(MgProcedureType procedureType, ReadonlyArray<MgInstanceVariable> sources) {
        MgValidator.checkInputCompatibility(procedureType.getProcedure(), sources);
        this.procedureType = procedureType;
        this.sources = sources;
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
