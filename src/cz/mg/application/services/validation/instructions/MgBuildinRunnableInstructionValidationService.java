package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.runtime.instructions.MgBuildinRunnableInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;


public class MgBuildinRunnableInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgBuildinRunnableInstruction instruction){
        if(instruction.getRunnable() == null){
            throw new ValidationException("Missing buildin runnable.");
        }
    }
}
