package cz.mg.application.services;

import cz.mg.application.entities.runtime.instructions.MgBranchingInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgLinearInstruction;
import cz.mg.application.entities.runtime.instructions.MgTerminatingInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.exceptions.InternalException;

import static cz.mg.application.services.MgInstructionCollectorService.collect;


public class MgInstructionValidationService extends MgInstructionService {
    public static void validate(MgProcedure procedure) {
        check(procedure);
        for(MgInstruction instruction : collect(procedure)){
            validateNotNull(instruction);
        }
    }

    private static void validateNotNull(MgInstruction instruction) {
        if(instruction == null){
            throw new InternalException("Validation failed for an instruction. Unexpected null instruction.");
        } else {
            if(instruction.getCommand() == null){
                throw new InternalException("Validation failed for an instruction. Command is null.");
            }

            if(instruction instanceof MgTerminatingInstruction){
                return;
            }

            if(instruction instanceof MgLinearInstruction){
                MgLinearInstruction linearInstruction = (MgLinearInstruction) instruction;
                if(linearInstruction.getNextInstruction() == null){
                    throw new InternalException("Validation failed for a linear instruction. Next instruction is null.");
                }
                return;
            }

            if(instruction instanceof MgBranchingInstruction){
                MgBranchingInstruction branchingInstruction = (MgBranchingInstruction) instruction;
                if(branchingInstruction.getTrueInstruction() == null){
                    throw new InternalException("Validation failed for a branching instruction. True instruction is null.");
                }
                if(branchingInstruction.getFalseInstruction() == null){
                    throw new InternalException("Validation failed for a branching instruction. False instruction is null.");
                }
                return;
            }

            throw new InternalException("Unsupported instruction of type " + instruction.getClass().getSimpleName() + ".");
        }
    }
}
