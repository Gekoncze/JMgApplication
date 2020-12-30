package cz.mg.application.entities.runtime.instructions.dummy;

import cz.mg.application.entities.runtime.instructions.MgLinearInstruction;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public class MgDummyInstruction extends MgLinearInstruction {
    public MgDummyInstruction(MgCommand command) {
        super(command);
    }
}
