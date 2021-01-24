package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.parts.instructions.MgSetValueToLocalInstruction;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.application.services.validation.MgValidator;


public class MgSetValueToLocalInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgSetValueToLocalInstruction instruction){
        if(instruction.getValue() == null){
            throw new ValidationException("Missing value.");
        }

        if(instruction.getDestination() == null){
            throw new ValidationException("Missing destination.");
        }

        MgValidator.checkOwnership(procedure, instruction.getDestination());
        MgValidator.checkCompatibility(instruction.getValue().getType().getAtom(), instruction.getDestination());
    }
}
