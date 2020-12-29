package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.dynamical.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.MgPart;
import cz.mg.collections.array.ReadableArray;


public abstract class MgCommand extends MgPart {
    @Optional @Link
    private MgCommand parent;

    @Optional @Part
    private ReadableArray<MgInstruction> instructions;

    public MgCommand() {
    }

    public MgCommand getParent() {
        return parent;
    }

    public void setParent(MgCommand parent) {
        this.parent = parent;
    }

    public ReadableArray<MgInstruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ReadableArray<MgInstruction> instructions) {
        this.instructions = instructions;
    }
}
