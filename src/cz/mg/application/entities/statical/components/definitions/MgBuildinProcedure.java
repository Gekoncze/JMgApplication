package cz.mg.application.entities.statical.components.definitions;

import cz.mg.application.entities.dynamical.objects.MgTask;


public abstract class MgBuildinProcedure extends MgProcedure {
    public MgBuildinProcedure() {
    }

    public abstract void run(MgTask task);
}
