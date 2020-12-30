package cz.mg.application.entities.statical.parts.commands;

import cz.mg.application.entities.statical.parts.commands.interfaces.MgDependentCommand;
import cz.mg.application.entities.statical.parts.commands.interfaces.MgMultiLineCommand;


public class MgTryCommand extends MgBlockCommand implements MgMultiLineCommand, MgDependentCommand {
    public MgTryCommand() {
    }
}
