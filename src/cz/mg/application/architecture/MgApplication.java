package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.MgIdentity;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public class MgApplication extends MgModule {
    @Mandatory @Part
    private final List<MgCore> cores = new List<>();

    @Mandatory @Part
    private final List<MgThread> threads = new List<>();

    public MgApplication(Text name, MgIdentity identity) {
        super(name, identity);
    }

    public List<MgCore> getCores() {
        return cores;
    }

    public List<MgThread> getThreads() {
        return threads;
    }
}
