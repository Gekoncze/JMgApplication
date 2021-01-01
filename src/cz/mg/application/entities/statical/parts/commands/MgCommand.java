package cz.mg.application.entities.statical.parts.commands;

import cz.mg.application.entities.statical.parts.MgPart;
import cz.mg.application.entities.statical.parts.commands.interfaces.*;
import cz.mg.application.services.exceptions.InternalException;


public abstract class MgCommand extends MgPart implements MgAnyCommand {
    public MgCommand() {
        if(!(this instanceof MgSingleLineCommand || this instanceof MgMultiLineCommand)){
            throw new InternalException("Command must be marked as single line or multi line.");
        }

        if(this instanceof MgSingleLineCommand && this instanceof MgMultiLineCommand){
            throw new InternalException("Command cannot be marked as both single line and multi line.");
        }

        if(!(this instanceof MgStandaloneCommand || this instanceof MgDependentCommand)){
            throw new InternalException("Command must be marked as standalone or dependent.");
        }

        if(this instanceof MgStandaloneCommand && this instanceof MgDependentCommand){
            throw new InternalException("Command cannot be marked as both standalone and dependent.");
        }
    }
}
