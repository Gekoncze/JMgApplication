package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgLeaveTaskInstruction;
import cz.mg.application.entities.statical.parts.commands.MgReturnCommand;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.collections.list.List;


public class MgReturnCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgReturnCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        return new List<>(new MgLeaveTaskInstruction());
    }
}
