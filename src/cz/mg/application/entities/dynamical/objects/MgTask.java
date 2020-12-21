package cz.mg.application.entities.dynamical.objects;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Parent;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.dynamical.types.MgProcedureType;
import cz.mg.application.entities.dynamical.instructions.MgInstruction;


public class MgTask extends MgStructuredObject {
    @Mandatory @Parent
    private final MgThread thread;

    @Optional @Link
    private MgInstruction instruction;

    public MgTask(MgProcedureType type, MgThread thread) {
        super(type);
        this.thread = thread;
    }

    @Override
    public MgProcedureType getType() {
        return (MgProcedureType) super.getType();
    }

    public MgThread getThread() {
        return thread;
    }

    public MgInstruction getInstruction() {
        return instruction;
    }

    public void setInstruction(MgInstruction instruction) {
        this.instruction = instruction;
    }
}
