package cz.mg.application.entities.runtime.instructions;


public class MgGotoInstruction extends MgLinearInstruction {
    public MgGotoInstruction(MgInstruction nextInstruction) {
        super(nextInstruction);
    }
}
