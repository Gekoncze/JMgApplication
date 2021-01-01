package cz.mg.application.entities.runtime.instructions;

import cz.mg.application.entities.runtime.MgRuntimeEntity;
import cz.mg.application.entities.runtime.objects.MgTask;


public abstract class MgInstruction extends MgRuntimeEntity {
    public MgInstruction() {
    }

    public abstract void run(MgTask task);
}
