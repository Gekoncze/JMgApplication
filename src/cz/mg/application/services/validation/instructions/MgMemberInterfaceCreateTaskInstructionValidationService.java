package cz.mg.application.services.validation.instructions;

import cz.mg.application.entities.runtime.instructions.MgMemberInterfaceCreateTaskInstruction;
import cz.mg.application.entities.runtime.types.MgClassType;
import cz.mg.application.entities.runtime.types.MgType;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.application.services.validation.MgValidator;


public class MgMemberInterfaceCreateTaskInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, MgMemberInterfaceCreateTaskInstruction instruction){
        if(instruction.getParent() == null){
            throw new ValidationException("Missing parent.");
        }

        if(instruction.getInterface() == null){
            throw new ValidationException("Missing interface.");
        }

        if(instruction.getSources() == null){
            throw new ValidationException("Missing sources.");
        }

        MgType type = instruction.getParent().getDefinition().getType();
        MgValidator.checkType(type, MgClassType.class, classType -> {
            if(!classType.getInterfaces().contains(instruction.getInterface())){
                throw new ValidationException("Class " + classType.getClazz().getName() + " does not contain interface " + instruction.getInterface().getName() + ".");
            }
        });
        MgValidator.checkOwnership(procedure, instruction.getSources());
    }
}
