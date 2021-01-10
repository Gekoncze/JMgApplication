package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.runtime.instructions.MgCreateTaskInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.application.services.validation.MgValidator;


public class MgCreateTaskInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgCreateTaskInstruction instruction){
        if(instruction.getProcedureType() == null){
            throw new ValidationException("Missing procedure type.");
        }

        if(instruction.getSources() == null){
            throw new ValidationException("Missing sources.");
        }

        MgValidator.checkOwnership(procedure, instruction.getSources());
        MgValidator.checkInputCompatibility(instruction.getProcedureType().getProcedure(), instruction.getSources());
    }
}
