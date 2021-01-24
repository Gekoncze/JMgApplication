package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.parts.instructions.MgLinearInstruction;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;


public class MgLinearInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgLinearInstruction instruction){
        if(instruction.getNextInstruction() == null){
            throw new ValidationException("Missing next instruction.");
        }
    }
}
