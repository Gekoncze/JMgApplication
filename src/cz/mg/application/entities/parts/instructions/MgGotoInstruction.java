package cz.mg.application.entities.parts.instructions;


public class MgGotoInstruction extends MgLinearInstruction {
    public MgGotoInstruction(MgInstruction nextInstruction) {
        super(nextInstruction);
    }
}
