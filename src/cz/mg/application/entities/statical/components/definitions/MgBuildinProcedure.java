package cz.mg.application.entities.statical.components.definitions;

import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.collections.text.Text;


public abstract class MgBuildinProcedure extends MgProcedure {
    public MgBuildinProcedure(Text name) {
        super(name);
    }

    public abstract void run(MgTask task);
}
