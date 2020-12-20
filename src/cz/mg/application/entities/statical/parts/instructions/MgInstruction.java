package cz.mg.application.entities.statical.parts.instructions;

import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.dynamical.objects.MgTask;


public abstract class MgInstruction extends MgEntity {
    public MgInstruction() {
    }

    public abstract void run(MgTask task);
}
