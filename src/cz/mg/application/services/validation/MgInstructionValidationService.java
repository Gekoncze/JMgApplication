package cz.mg.application.services.validation;

import cz.mg.application.entities.runtime.instructions.*;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.application.services.validation.instructions.*;


public class MgInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure){
        for(MgInstruction instruction : procedure.getType().getInstructions()){
            validate(procedure, instruction);
        }
    }

    public static void validate(MgProcedure procedure, MgInstruction instruction){
        if(instruction instanceof MgBranchingInstruction){
            MgBranchInstructionValidationService.validate(
                procedure, (MgBranchingInstruction) instruction
            );
        }

        if(instruction instanceof MgBuildinRunnableInstruction){
            MgBuildinRunnableInstructionValidationService.validate(
                procedure, (MgBuildinRunnableInstruction) instruction
            );
        }

        if(instruction instanceof MgCreateTaskInstruction){
            MgCreateTaskInstructionValidationService.validate(
                procedure, (MgCreateTaskInstruction) instruction
            );
        }

        if(instruction instanceof MgDestroyTaskInstruction){
            MgDestroyTaskInstructionValidationService.validate(
                procedure, (MgDestroyTaskInstruction) instruction
            );
        }

        if(instruction instanceof MgDummyInstruction){
            MgDummyInstructionValidationService.validate(
                procedure, (MgDummyInstruction) instruction
            );
        }

        if(instruction instanceof MgEnterTaskInstruction){
            MgEnterTaskInstructionValidationService.validate(
                procedure, (MgEnterTaskInstruction) instruction
            );
        }

        if(instruction instanceof MgGotoInstruction){
            MgGotoInstructionValidationService.validate(
                procedure, (MgGotoInstruction) instruction
            );
        }

        if(instruction instanceof MgLeaveTaskInstruction){
            MgLeaveTaskInstructionValidationService.validate(
                procedure, (MgLeaveTaskInstruction) instruction
            );
        }

        if(instruction instanceof MgMemberInterfaceCreateTaskInstruction){
            MgMemberInterfaceCreateTaskInstructionValidationService.validate(
                procedure, (MgMemberInterfaceCreateTaskInstruction) instruction
            );
        }

        if(instruction instanceof MgMemberVariableInstruction){
            MgMemberVariableInstructionValidationService.validate(
                procedure, (MgMemberVariableInstruction) instruction
            );
        }

        if(instruction instanceof MgRollbackInstruction){
            MgRollbackInstructionValidationService.validate(
                procedure, (MgRollbackInstruction) instruction
            );
        }

        if(instruction instanceof MgSetLocalToLocalInstruction){
            MgSetLocalToLocalInstructionValidationService.validate(
                procedure, (MgSetLocalToLocalInstruction) instruction
            );
        }

        if(instruction instanceof MgSetLocalToMemberInstruction){
            MgSetLocalToMemberInstructionValidationService.validate(
                procedure, (MgSetLocalToMemberInstruction) instruction
            );
        }

        if(instruction instanceof MgSetValueToLocalInstruction){
            MgSetValueToLocalInstructionValidationService.validate(
                procedure, (MgSetValueToLocalInstruction) instruction
            );
        }

        throw new InternalException("Unsupported validation for instruction " + instruction.getClass().getSimpleName() + ".");
    }
}
