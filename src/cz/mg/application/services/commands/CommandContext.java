package cz.mg.application.services.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.MgCommand;


class CommandContext {
    @Mandatory @Link
    private final MgCommand command;

    @Optional @Link
    private final CommandContext parent;

    @Optional @Link
    private final MgInstruction next;

    @Optional @Link
    private final MgInstruction begin;

    @Optional @Link
    private final MgInstruction end;

    public CommandContext(MgCommand command, CommandContext parent, MgInstruction next, MgInstruction begin, MgInstruction end) {
        this.command = command;
        this.parent = parent;
        this.next = next;
        this.begin = begin;
        this.end = end;
    }

    public CommandContext(MgCommand command, CommandContext parent, MgInstruction next) {
        this.command = command;
        this.parent = parent;
        this.next = next;
        this.begin = null;
        this.end = null;
    }

    public MgCommand getCommand() {
        return command;
    }

    public CommandContext getParent() {
        return parent;
    }

    public MgInstruction getNext() {
        return next;
    }

    public MgInstruction getBegin() {
        return begin;
    }

    public MgInstruction getEnd() {
        return end;
    }
}
