package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.parts.MgVariable;


public class MgRollbackInstruction extends MgInstruction {
    @Optional @Link
    private MgVariable variable;

    public MgRollbackInstruction() {
    }

    public MgVariable getVariable() {
        return variable;
    }

    public void setVariable(MgVariable variable) {
        this.variable = variable;
    }

    @Override
    public void run(MgTask task) {
        task.getThread().setException(
            task.getObject(variable)
        );
    }
}
