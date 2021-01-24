package cz.mg.application.entities.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Part;
import cz.mg.application.Store;
import cz.mg.collections.list.List;


@Store
public class MgApplication {
    @Mandatory @Part
    private final MgModule module = new MgModule();

    @Mandatory @Part
    private final List<MgCore> cores = new List<>();

    @Mandatory @Part
    private final List<MgThread> threads = new List<>();

    @Mandatory @Cache
    private final List<MgModule> modules = new List<>();

    public MgApplication() {
    }

    public MgModule getModule() {
        return module;
    }

    public List<MgCore> getCores() {
        return cores;
    }

    public List<MgThread> getThreads() {
        return threads;
    }

    public List<MgModule> getModules() {
        return modules;
    }
}
