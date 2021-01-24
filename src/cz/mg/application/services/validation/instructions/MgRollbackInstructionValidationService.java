package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.parts.instructions.MgRollbackInstruction;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;


public class MgRollbackInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgRollbackInstruction instruction){
        if(instruction.getVariable() == null){
            throw new ValidationException("Missing variable.");
        }
    }
}
