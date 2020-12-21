package cz.mg.application.entities.dynamical.instructions;

import cz.mg.application.entities.dynamical.MgDynamicalEntity;
import cz.mg.application.entities.dynamical.objects.MgTask;


public abstract class MgInstruction extends MgDynamicalEntity {
    public MgInstruction() {
    }

    public abstract void run(MgTask task);
}
