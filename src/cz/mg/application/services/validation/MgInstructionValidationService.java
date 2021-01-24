package cz.mg.application.services.validation;

import cz.mg.application.entities.parts.instructions.*;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.application.services.validation.instructions.*;
import cz.mg.collections.list.List;


public class MgInstructionValidationService extends MgService {
    public static void validate(MgProcedure procedure, List<MgInstruction> instructions){
        if(instructions.count() < 1){
            throw new InternalException("Missing instructions.");
        }

        for(MgInstruction instruction : procedure.getType().getInstructions()){
            validate(procedure, instruction);
        }
    }

    private static void validate(MgProcedure procedure, MgInstruction instruction){
        if(instruction instanceof MgLinearInstruction){
            MgLinearInstructionValidationService.validate(
                procedure, (MgLinearInstruction) instruction
            );
        }

        if(instruction instanceof MgBranchingInstruction){
            MgBranchInstructionValidationService.validate(
                procedure, (MgBranchingInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgBuildinRunnableInstruction){
            MgBuildinRunnableInstructionValidationService.validate(
                procedure, (MgBuildinRunnableInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgCreateTaskInstruction){
            MgCreateTaskInstructionValidationService.validate(
                procedure, (MgCreateTaskInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgDestroyTaskInstruction){
            MgDestroyTaskInstructionValidationService.validate(
                procedure, (MgDestroyTaskInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgDummyInstruction){
            MgDummyInstructionValidationService.validate(
                procedure, (MgDummyInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgEnterTaskInstruction){
            MgEnterTaskInstructionValidationService.validate(
                procedure, (MgEnterTaskInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgGotoInstruction){
            MgGotoInstructionValidationService.validate(
                procedure, (MgGotoInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgLeaveTaskInstruction){
            MgLeaveTaskInstructionValidationService.validate(
                procedure, (MgLeaveTaskInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgMemberInterfaceCreateTaskInstruction){
            MgMemberInterfaceCreateTaskInstructionValidationService.validate(
                procedure, (MgMemberInterfaceCreateTaskInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgMemberVariableInstruction){
            MgMemberVariableInstructionValidationService.validate(
                procedure, (MgMemberVariableInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgRollbackInstruction){
            MgRollbackInstructionValidationService.validate(
                procedure, (MgRollbackInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgSetLocalToLocalInstruction){
            MgSetLocalToLocalInstructionValidationService.validate(
                procedure, (MgSetLocalToLocalInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgSetLocalToMemberInstruction){
            MgSetLocalToMemberInstructionValidationService.validate(
                procedure, (MgSetLocalToMemberInstruction) instruction
            );
            return;
        }

        if(instruction instanceof MgSetValueToLocalInstruction){
            MgSetValueToLocalInstructionValidationService.validate(
                procedure, (MgSetValueToLocalInstruction) instruction
            );
            return;
        }

        throw new InternalException("Unsupported validation for instruction " + instruction.getClass().getSimpleName() + ".");
    }
}
