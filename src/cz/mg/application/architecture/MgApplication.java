package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public class MgApplication extends MgModule {
    @Mandatory @Part
    private final List<MgCore> cores = new List<>();

    @Mandatory @Part
    private final List<MgThread> threads = new List<>();

    public MgApplication() {
    }

    public List<MgCore> getCores() {
        return cores;
    }

    public List<MgThread> getThreads() {
        return threads;
    }
}
