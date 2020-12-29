package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.Named;
import cz.mg.application.entities.statical.MgStaticalEntity;
import cz.mg.application.entities.statical.components.MgLocation;
import cz.mg.application.entities.statical.parts.MgIdentity;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public class MgModule extends MgStaticalEntity implements Named {
    @Mandatory @Value
    private Text name;

    @Mandatory @Value
    private MgIdentity identity;

    @Mandatory @Part
    private final MgSequence sequence = new MgSequence();

    @Mandatory @Part
    private final MgLocation root = new MgLocation();

    @Mandatory @Part
    private final List<MgDependency> dependencies = new List<>();

    @Mandatory @Cache
    private final List<MgModule> modules = new List<>();

    public MgModule() {
    }

    @Override
    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public MgIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(MgIdentity identity) {
        this.identity = identity;
    }

    public MgSequence getSequence() {
        return sequence;
    }

    public MgLocation getRoot() {
        return root;
    }

    public List<MgDependency> getDependencies() {
        return dependencies;
    }

    public List<MgModule> getModules() {
        return modules;
    }
}
