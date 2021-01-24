package cz.mg.application.entities.parts.instructions;

import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.objects.MgTask;


public abstract class MgInstruction extends MgEntity {
    public MgInstruction() {
    }

    public abstract void run(MgTask task);
}
