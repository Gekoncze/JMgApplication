package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.parts.instructions.MgMemberVariableInstruction;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.application.services.validation.MgValidator;


public class MgMemberVariableInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgMemberVariableInstruction instruction){
        if(instruction.getParent() == null){
            throw new ValidationException("Missing parent.");
        }

        if(instruction.getChild() == null){
            throw new ValidationException("Missing child.");
        }

        if(instruction.getOutput() == null){
            throw new ValidationException("Missing output.");
        }

        MgValidator.checkOwnership(procedure, instruction.getOutput());
        MgValidator.checkOwnership(instruction.getParent().getDefinition(), instruction.getChild());
        MgValidator.checkCompatibility(instruction.getChild(), instruction.getOutput());
    }
}
