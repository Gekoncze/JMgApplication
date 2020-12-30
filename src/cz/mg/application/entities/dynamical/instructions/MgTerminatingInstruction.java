package cz.mg.application.entities.dynamical.instructions;

import cz.mg.application.entities.statical.parts.commands.MgCommand;


public abstract class MgTerminatingInstruction extends MgInstruction {
    public MgTerminatingInstruction(MgCommand command) {
        super(command);
    }
}
