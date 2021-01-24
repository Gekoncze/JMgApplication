package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.buildin.atoms.bool8.MgBool8;
import cz.mg.application.entities.parts.instructions.MgBranchingInstruction;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.application.services.validation.MgValidator;


public class MgBranchInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgBranchingInstruction instruction){
        if(instruction.getTrueInstruction() == null){
            throw new ValidationException("Missing true branch instruction.");
        }

        if(instruction.getFalseInstruction() == null){
            throw new ValidationException("Missing false branch instruction.");
        }

        MgValidator.checkCompatibility(instruction.getCondition(), MgBool8.getInstance());
    }
}
