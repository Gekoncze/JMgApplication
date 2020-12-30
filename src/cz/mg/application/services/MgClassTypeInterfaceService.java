package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.collections.list.List;


public class MgClassTypeInterfaceService extends MgService {
    public static MgProcedure resolveProcedure(MgClass clazz, MgInterface mgInterface){
        List<MgProcedure> baseProcedures = collectBaseProcedures(clazz, mgInterface);
        List<MgProcedure> selfProcedures = collectSelfProcedures(clazz, mgInterface);

        MgProcedure baseProcedure = baseProcedures.isEmpty() ? null : baseProcedures.getFirst();
        MgProcedure selfProcedure = selfProcedures.isEmpty() ? null : selfProcedures.getFirst();

        for(MgProcedure procedure : baseProcedures){
            if(procedure != baseProcedure){
                baseProcedure = null;
                break;
            }
        }

        for(MgProcedure procedure : selfProcedures){
            if(procedure != selfProcedure){
                throw new RuntimeException("Multiple implementations of interface " + mgInterface.getName() + ".");
            }
        }

        MgProcedure procedure = selfProcedure != null ? selfProcedure : baseProcedure;

        if(procedure == null && !clazz.getOptions().isAbstract()){
            if(baseProcedures.isEmpty()){
                throw new RuntimeException("Missing implementation of interface '" + mgInterface.getName() + "'.");
            } else {
                throw new RuntimeException("Ambiguous implementation of interface '" + mgInterface.getName() + "'.");
            }
        } else {
            return procedure;
        }
    }

    private static List<MgProcedure> collectBaseProcedures(MgClass clazz, MgInterface mgInterface){
        List<MgProcedure> baseProcedures = new List<>();
        for(MgClass base : clazz.getBaseClasses()){
            baseProcedures.addLast(
                base.getType().getProcedureMap().get(mgInterface)
            );
        }
        return baseProcedures;
    }

    private static List<MgProcedure> collectSelfProcedures(MgClass clazz, MgInterface mgInterface){
        List<MgProcedure> selfProcedures = new List<>();
        for(MgProcedure procedure : clazz.getProcedures()){
            if(procedure.getInterface() == mgInterface){
                selfProcedures.addLast(procedure);
            }
        }
        return selfProcedures;
    }
}
