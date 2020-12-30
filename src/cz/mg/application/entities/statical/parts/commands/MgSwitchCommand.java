package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgMultiLineCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.collections.list.List;


public class MgSwitchCommand extends MgCommand implements MgMultiLineCommand, MgStandaloneCommand {
    @Mandatory @Part
    private final List<MgCaseCommand> caseCommands = new List<>();

    public MgSwitchCommand() {
    }

    public List<MgCaseCommand> getCaseCommands() {
        return caseCommands;
    }
}
