package cz.mg.application.entities.statical.parts.commands.interfaces;

import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.collections.Clump;


public interface MgMultiLineCommand {
    Clump<MgCommand> getCommands();
}
