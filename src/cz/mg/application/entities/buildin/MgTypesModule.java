package cz.mg.application.entities.buildin;

import cz.mg.application.entities.buildin.atoms.bool8.MgBool8;
import cz.mg.application.entities.buildin.atoms.float32.MgFloat32;
import cz.mg.application.entities.buildin.atoms.int32.MgInt32;
import cz.mg.application.architecture.MgModule;
import cz.mg.application.entities.statical.parts.MgIdentity;
import cz.mg.application.entities.statical.parts.MgVersion;
import cz.mg.collections.text.Text;


public class MgTypesModule extends MgModule {
    private static MgTypesModule instance;

    public static MgTypesModule getInstance() {
        if(instance == null) instance = new MgTypesModule();
        return instance;
    }

    public MgTypesModule() {
        super(
            new Text("types"),
            new MgIdentity(
                new Text("cz.mg.application.buildin.types"),
                new MgVersion(0, 0, 0)
            )
        );
    }

    public MgTypesModule init(){
        getRoot().getComponents().addLast(MgBool8.getInstance().init());
        getRoot().getComponents().addLast(MgInt32.getInstance().init());
        getRoot().getComponents().addLast(MgFloat32.getInstance().init());
        return this;
    }
}
