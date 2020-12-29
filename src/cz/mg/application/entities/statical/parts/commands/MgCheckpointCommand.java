package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public class MgCheckpointCommand extends MgCommand {
    @Optional @Part
    private MgTryCommand tryCommand;

    @Mandatory @Part
    private final List<MgCatchCommand> catchCommands = new List<>();

    public MgCheckpointCommand() {
    }

    public MgTryCommand getTryCommand() {
        return tryCommand;
    }

    public void setTryCommand(MgTryCommand tryCommand) {
        this.tryCommand = tryCommand;
    }

    public List<MgCatchCommand> getCatchCommands() {
        return catchCommands;
    }
}
