package cz.mg.application.entities.runtime;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgAnyCommand;
import cz.mg.collections.array.ReadableArray;


public class MgRuntimeCommand extends MgRuntimeEntity {
    @Mandatory @Link
    private final MgCommand command;

    @Mandatory @Link
    private final MgCommand parent;

    @Mandatory @Part
    private final ReadableArray<MgInstruction> instructions;

    @Mandatory @Part
    private final ReadableArray<MgAnyCommand> commands;

    public MgRuntimeCommand(
        MgCommand command,
        MgCommand parent,
        ReadableArray<MgInstruction> instructions,
        ReadableArray<MgAnyCommand> commands
    ) {
        this.command = command;
        this.parent = parent;
        this.instructions = instructions;
        this.commands = commands;
    }

    public MgCommand getCommand() {
        return command;
    }

    public MgCommand getParent() {
        return parent;
    }

    public ReadableArray<MgInstruction> getInstructions() {
        return instructions;
    }

    public ReadableArray<MgAnyCommand> getCommands() {
        return commands;
    }
}
