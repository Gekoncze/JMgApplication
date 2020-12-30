package cz.mg.application.entities.runtime.instructions;

import cz.mg.application.entities.statical.parts.commands.MgCommand;


public class MgGotoInstruction extends MgLinearInstruction {
    public MgGotoInstruction(MgCommand command, MgInstruction nextInstruction) {
        super(command, nextInstruction);
    }
}
