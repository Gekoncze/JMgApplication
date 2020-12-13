package cz.mg.application.entities.dynamical;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.dynamical.instructions.MgInstruction;
import cz.mg.application.entities.dynamical.types.MgProcedureType;
import cz.mg.collections.map.Map;


public class MgTask extends MgObject {
    @Optional @Link
    private MgInstruction instruction;

    public MgTask(MgProcedureType type) {
        super(type, new Map<>());
    }

    public MgInstruction getInstruction() {
        return instruction;
    }

    public void setInstruction(MgInstruction instruction) {
        this.instruction = instruction;
    }
}
