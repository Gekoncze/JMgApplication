package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.runtime.instructions.MgSetLocalToLocalInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.application.services.validation.MgValidator;


public class MgSetLocalToLocalInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgSetLocalToLocalInstruction instruction){
        if(instruction.getSource() == null){
            throw new ValidationException("Missing source.");
        }

        if(instruction.getDestination() == null){
            throw new ValidationException("Missing destination.");
        }

        MgValidator.checkOwnership(procedure, instruction.getSource());
        MgValidator.checkOwnership(procedure, instruction.getDestination());
        MgValidator.checkCompatibility(instruction.getSource(), instruction.getDestination());
    }
}
