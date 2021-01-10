package cz.mg.application.services;

import cz.mg.application.entities.runtime.instructions.MgBranchingInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgLinearInstruction;
import cz.mg.application.entities.runtime.instructions.MgDummyInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.collections.Clump;
import cz.mg.collections.list.List;


public class MgInstructionOptimizationService extends MgService {
    public static void optimize(MgProcedure procedure){
        Clump<MgInstruction> instructions = procedure.getType().getInstructions();
        List<MgDummyInstruction> dummyInstructions = new List<>();

        for(MgInstruction instruction : instructions){
            if(instruction instanceof MgDummyInstruction){
                dummyInstructions.addLast((MgDummyInstruction) instruction);
            }
        }

        for(MgDummyInstruction dummyInstruction : dummyInstructions){
            optimize(dummyInstruction, instructions);
        }
    }

    private static void optimize(MgDummyInstruction dummyInstruction, Clump<MgInstruction> instructions){
        for(MgInstruction instruction : instructions){
            optimize(dummyInstruction, instruction);
        }
    }

    private static void optimize(MgDummyInstruction dummyInstruction, MgInstruction instruction){
        if(instruction instanceof MgLinearInstruction){
            MgLinearInstruction linearInstruction = (MgLinearInstruction) instruction;
            if(linearInstruction.getNextInstruction() == dummyInstruction){
                linearInstruction.setNextInstruction(dummyInstruction.getNextInstruction());
            }
        }

        if(instruction instanceof MgBranchingInstruction){
            MgBranchingInstruction branchingInstruction = (MgBranchingInstruction) instruction;
            if(branchingInstruction.getTrueInstruction() == dummyInstruction){
                branchingInstruction.setTrueInstruction(dummyInstruction.getNextInstruction());
            }
            if(branchingInstruction.getFalseInstruction() == dummyInstruction){
                branchingInstruction.setFalseInstruction(dummyInstruction.getNextInstruction());
            }
        }
    }
}
