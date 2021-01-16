package cz.mg.application.services;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgLinearInstruction;
import cz.mg.collections.list.List;


public class MgInstructionConnectionService extends MgService {
    public static void connect(List<MgInstruction> instructions, MgInstruction nextInstruction){
        for(MgInstruction instruction : reverse(instructions)){
            MgLinearInstruction linearInstruction = (MgLinearInstruction) instruction;
            linearInstruction.setNextInstruction(nextInstruction);
            nextInstruction = instruction;
        }
    }

    private static List<MgInstruction> reverse(List<MgInstruction> instructions){
        List<MgInstruction> reversed = new List<>();
        for(MgInstruction instruction : instructions) reversed.addFirst(instruction);
        return reversed;
    }
}
