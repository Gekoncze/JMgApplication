package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.exceptions.LogicalException;


public abstract class MgInstructionService {
    protected static void check(MgProcedure procedure){
        if(procedure.getCommands().isEmpty()){
            throw new LogicalException(procedure, "Procedure must have at least one command.");
        }
    }
}
