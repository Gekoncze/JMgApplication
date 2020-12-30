package cz.mg.application.services;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgAnyCommand;
import cz.mg.collections.Clump;
import cz.mg.collections.list.List;


public class MgInstructionCollectorService extends MgService {
    public static List<MgInstruction> collect(MgProcedure procedure) {
        List<MgInstruction> instructions = new List<>();
        collect(procedure.getCommands(), instructions);
        return instructions;
    }

    private static void collect(Clump<? extends MgAnyCommand> commands, List<MgInstruction> instructions) {
        for(MgAnyCommand command : commands){
            collect(command, instructions);
        }
    }

    private static void collect(MgAnyCommand command, List<MgInstruction> instructions) {
        for(MgInstruction instruction : command.getRuntimeCommand().getInstructions()){
            instructions.addLast(instruction);
        }

        collect(command.getRuntimeCommand().getCommands(), instructions);
    }
}
