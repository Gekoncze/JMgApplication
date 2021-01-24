package cz.mg.application.services;

import cz.mg.application.entities.architecture.MgModule;
import cz.mg.application.entities.components.MgComponent;
import cz.mg.application.entities.components.MgLocation;
import cz.mg.application.entities.components.definitions.MgClass;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.services.exceptions.InternalException;


public class MgCompileModuleService extends MgService {
    public static void compile(MgModule module){
        compileLocation(module.getRoot());
    }

    private static void compileComponent(MgComponent component){
        if(component instanceof MgLocation){
            compileLocation((MgLocation) component);
            return;
        }

        if(component instanceof MgClass){
            compileClass((MgClass) component);
            return;
        }

        if(component instanceof MgProcedure){
            compileProcedure((MgProcedure) component);
            return;
        }

        throw new InternalException(component, "Unsupported component '" + component.getName() + "'.");
    }

    private static void compileLocation(MgLocation location){
        for(MgComponent component : location.getComponents()){
            compileComponent(component);
        }
    }

    private static void compileClass(MgClass clazz){
        MgClassTypeService.create(clazz);

        for(MgProcedure procedure : clazz.getProcedures()){
            compileProcedure(procedure);
        }
    }

    private static void compileProcedure(MgProcedure procedure){
        MgProcedureTypeService.create(procedure);
    }
}
