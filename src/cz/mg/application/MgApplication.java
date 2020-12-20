package cz.mg.application;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.MgEntity;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.statical.components.MgLocation;
import cz.mg.application.entities.statical.parts.MgIdentity;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public class MgApplication extends MgEntity implements Named {
    @Mandatory @Value
    private final Text name;

    @Mandatory @Value
    private final MgIdentity identity;

    @Mandatory @Part
    private final MgLocation root = new MgLocation(new Text());

    @Mandatory @Part
    private final List<MgThread> threads = new List<>();

    public MgApplication(Text name, MgIdentity identity) {
        this.name = name;
        this.identity = identity;
    }

    @Override
    public Text getName() {
        return name;
    }

    public MgIdentity getIdentity() {
        return identity;
    }

    public MgLocation getRoot() {
        return root;
    }

    public List<MgThread> getThreads() {
        return threads;
    }
}
