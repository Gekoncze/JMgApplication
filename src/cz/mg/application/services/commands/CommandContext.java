package cz.mg.application.services.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


public class CommandContext {
    @Optional @Link
    private final MgCommand command;

    @Optional @Link
    private final CommandContext parent;

    @Optional @Link
    private final MgInstruction begin;

    @Optional @Link
    private final MgInstruction end;

    public CommandContext(CommandContext commandContext, MgInstruction end) {
        this.command = commandContext.command;
        this.parent = commandContext.parent;
        this.begin = commandContext.begin;
        this.end = end;
    }

    public CommandContext(MgCommand command, CommandContext parent, MgInstruction begin, MgInstruction end) {
        this.command = command;
        this.parent = parent;
        this.begin = begin;
        this.end = end;
    }

    public MgCommand getCommand() {
        return command;
    }

    public CommandContext getParent() {
        return parent;
    }

    public MgInstruction getBegin() {
        return begin;
    }

    public MgInstruction getEnd() {
        return end;
    }
}
