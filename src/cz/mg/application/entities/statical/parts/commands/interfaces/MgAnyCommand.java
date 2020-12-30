package cz.mg.application.entities.statical.parts.commands.interfaces;

import cz.mg.application.entities.runtime.MgRuntimeCommand;


public interface MgAnyCommand {
    MgRuntimeCommand getRuntimeCommand();
    void setRuntimeCommand(MgRuntimeCommand runtimeCommand);
}
