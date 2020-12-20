package cz.mg.application.entities.dynamical;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.MgApplication;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.collections.list.List;


public class MgThread {
    @Mandatory @Link
    private final MgApplication application;

    @Mandatory @Part
    private final List<MgTask> stack = new List<>();

    public MgThread(MgApplication application) {
        this.application = application;
    }

    public MgApplication getApplication() {
        return application;
    }

    public List<MgTask> getStack() {
        return stack;
    }
}
