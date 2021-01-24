package cz.mg.application.entities.parts.commands;

import cz.mg.application.entities.parts.commands.interfaces.MgSingleLineCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgStandaloneCommand;


public class MgReturnCommand extends MgCommand implements MgSingleLineCommand, MgStandaloneCommand {
    public MgReturnCommand() {
    }
}
