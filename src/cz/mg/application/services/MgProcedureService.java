package cz.mg.application.services;

import cz.mg.application.entities.dynamical.instructions.MgInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgMultiLineCommand;
import cz.mg.collections.Clump;
import cz.mg.collections.list.List;


public class MgProcedureService extends MgService {
    public static List<MgInstruction> collectInstructions(MgProcedure procedure) {
        List<MgInstruction> instructions = new List<>();
        collectInstructions(procedure.getCommands(), instructions);
        return instructions;
    }

    private static void collectInstructions(Clump<MgCommand> commands, List<MgInstruction> instructions) {
        for(MgCommand command : commands){
            collectInstructions(command, instructions);
        }
    }

    private static void collectInstructions(MgCommand command, List<MgInstruction> instructions) {
        for(MgInstruction instruction : command.getInstructions()){
            instructions.addLast(instruction);
        }

        if(command instanceof MgMultiLineCommand){
            MgMultiLineCommand multiLineCommand = (MgMultiLineCommand) command;
            collectInstructions(multiLineCommand.getCommands(), instructions);
        }
    }
}
