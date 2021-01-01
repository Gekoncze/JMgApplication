package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.application.services.exceptions.LogicalException;


public abstract class MgInstructionService {
    protected static void check(MgProcedure procedure){
        if(procedure.getType() == null){
            throw new InternalException(procedure, "Procedure type was not created yet.");
        }

        if(procedure.getType().getInstructions().count() < 1){
            throw new LogicalException(procedure, "Missing instructions. Procedure must have at least one command.");
        }
    }
}
