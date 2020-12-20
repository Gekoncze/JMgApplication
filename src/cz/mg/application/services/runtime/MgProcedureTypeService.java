package cz.mg.application.services.runtime;

import cz.mg.application.entities.dynamical.types.MgProcedureType;
import cz.mg.application.entities.statical.parts.MgProcedure;
import cz.mg.application.services.MgService;


public class MgProcedureTypeService extends MgService {
    public static void create(MgProcedure procedure){
        // todo - create instructions from commands
        procedure.setType(new MgProcedureType(procedure));
    }
}
