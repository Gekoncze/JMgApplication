package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgMultiLineCommand;
import cz.mg.collections.Clump;
import cz.mg.collections.list.List;


public class MgCheckpointCommand extends MgCommand implements MgMultiLineCommand {
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

    @Override
    public Clump<MgCommand> getCommands() {
        List<MgCommand> commands = new List<>(tryCommand);
        commands.addCollectionLast(catchCommands);
        return commands;
    }
}
