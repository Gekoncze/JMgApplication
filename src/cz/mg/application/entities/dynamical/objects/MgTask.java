package cz.mg.application.entities.dynamical.objects;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.dynamical.instructions.MgInstruction;
import cz.mg.application.entities.dynamical.types.MgProcedureType;


public class MgTask extends MgStructuredObject {
    @Optional @Link
    private MgInstruction instruction;

    public MgTask(MgProcedureType type, MgInstruction instruction) {
        super(type);
        this.instruction = instruction;
    }

    @Override
    public MgProcedureType getType() {
        return (MgProcedureType) super.getType();
    }

    public MgInstruction getInstruction() {
        return instruction;
    }

    public void setInstruction(MgInstruction instruction) {
        this.instruction = instruction;
    }
}
