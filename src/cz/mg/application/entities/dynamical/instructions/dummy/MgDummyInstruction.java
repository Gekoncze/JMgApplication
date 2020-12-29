package cz.mg.application.entities.dynamical.instructions.dummy;

import cz.mg.application.entities.dynamical.instructions.MgLinearInstruction;
import cz.mg.application.entities.dynamical.objects.MgTask;


public class MgDummyInstruction extends MgLinearInstruction {
    public MgDummyInstruction() {
    }

    @Override
    public void run(MgTask task) {
        super.run(task);
    }
}
