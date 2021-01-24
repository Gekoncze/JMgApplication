package cz.mg.application.entities.objects;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.types.MgProcedureType;


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

    public MgObject getObject(int i){
        return getObject(getType().getInstanceVariables().get(i));
    }

    public void setObject(int i, MgObject object){
        setObject(getType().getInstanceVariables().get(i), object);
    }
}
