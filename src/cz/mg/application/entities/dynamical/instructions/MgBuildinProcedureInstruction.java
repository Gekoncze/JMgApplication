package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgBuildinProcedure;


public class MgBuildinProcedureInstruction extends MgLinearInstruction {
    @Mandatory @Part
    private final MgBuildinProcedure buildinProcedure;

    public MgBuildinProcedureInstruction(MgBuildinProcedure buildinProcedure) {
        this.buildinProcedure = buildinProcedure;
    }

    @Override
    public void run(MgTask task) {
        buildinProcedure.run(task);
        task.setInstruction(getNextInstruction());
    }
}
