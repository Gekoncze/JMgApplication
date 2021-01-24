package cz.mg.application.entities.parts.commands;

import cz.mg.application.entities.parts.MgPart;
import cz.mg.application.entities.parts.commands.interfaces.*;
import cz.mg.application.services.MgInternalValidator;


public abstract class MgCommand extends MgPart implements MgAnyCommand {
    public MgCommand() {
        MgInternalValidator.checkInterfaceMandatory(this, MgSingleLineCommand.class, MgMultiLineCommand.class);
        MgInternalValidator.checkInterfaceMandatory(this, MgStandaloneCommand.class, MgDependentCommand.class);
    }
}
