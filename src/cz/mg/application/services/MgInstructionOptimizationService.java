package cz.mg.application.services;

import cz.mg.application.entities.parts.instructions.MgBranchingInstruction;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.instructions.MgLinearInstruction;
import cz.mg.application.entities.parts.instructions.MgDummyInstruction;
import cz.mg.collections.list.List;


public class MgInstructionOptimizationService extends MgService {
    public static void optimize(List<MgInstruction> instructions){
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

    private static void optimize(MgDummyInstruction dummyInstruction, List<MgInstruction> instructions){
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
