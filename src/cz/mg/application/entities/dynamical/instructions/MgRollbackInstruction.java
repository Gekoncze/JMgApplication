package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public class MgRollbackInstruction extends MgTerminatingInstruction {
    @Mandatory @Link
    private MgVariable variable;

    public MgRollbackInstruction(MgCommand command, MgVariable variable) {
        super(command);
        this.variable = variable;
    }

    public MgVariable getVariable() {
        return variable;
    }

    public void setVariable(MgVariable variable) {
        this.variable = variable;
    }

    @Override
    public void run(MgTask task) {
        MgThread.getInstance().setException(
            task.getObject(variable)
        );
    }
}
