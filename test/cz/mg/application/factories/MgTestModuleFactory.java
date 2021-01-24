package cz.mg.application.factories;

import cz.mg.application.entities.architecture.MgModule;
import cz.mg.collections.text.Text;


public interface MgTestModuleFactory {
    public static MgModule createModule(String name){
        MgModule module = new MgModule();
        module.setName(new Text(name));
        return module;
    }
}
