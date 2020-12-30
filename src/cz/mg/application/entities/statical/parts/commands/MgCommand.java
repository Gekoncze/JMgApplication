package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.dynamical.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.MgPart;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgMultiLineCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgSingleLineCommand;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.collections.array.ReadableArray;


public abstract class MgCommand extends MgPart {
    @Optional @Link
    private MgCommand parent;

    @Optional @Part
    private ReadableArray<MgInstruction> instructions;

    public MgCommand() {
        if(!(this instanceof MgSingleLineCommand || this instanceof MgMultiLineCommand)){
            throw new InternalException("Command must be marked as single line or multi line.");
        }

        if(this instanceof MgSingleLineCommand && this instanceof MgMultiLineCommand){
            throw new InternalException("Command cannot be marked as both single line and multi line.");
        }
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
