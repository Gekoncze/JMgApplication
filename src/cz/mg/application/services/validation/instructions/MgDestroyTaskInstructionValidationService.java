package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.runtime.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.application.services.validation.MgValidator;


public class MgDestroyTaskInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgDestroyTaskInstruction instruction){
        if(instruction.getDestinations() == null){
            throw new ValidationException("Missing destinations.");
        }

        MgValidator.checkOwnership(procedure, instruction.getDestinations());
    }
}
