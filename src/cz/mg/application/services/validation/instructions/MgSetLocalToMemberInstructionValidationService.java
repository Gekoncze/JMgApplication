package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.parts.instructions.MgSetLocalToMemberInstruction;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.application.services.validation.MgValidator;


public class MgSetLocalToMemberInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgSetLocalToMemberInstruction instruction){
        if(instruction.getSource() == null){
            throw new ValidationException("Missing source.");
        }

        if(instruction.getDestination() == null){
            throw new ValidationException("Missing destination.");
        }

        if(instruction.getDestinationMember() == null){
            throw new ValidationException("Missing destination member.");
        }

        MgValidator.checkOwnership(procedure, instruction.getSource());
        MgValidator.checkOwnership(instruction.getDestination().getDefinition(), instruction.getDestinationMember());
        MgValidator.checkCompatibility(instruction.getSource(), instruction.getDestinationMember());
    }
}
