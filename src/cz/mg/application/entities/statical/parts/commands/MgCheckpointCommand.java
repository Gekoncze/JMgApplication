package cz.mg.application.entities.statical.parts.commands;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;


public class MgCheckpointCommand extends MgCommand {
    @Optional @Part
    private MgTryCommand tryCommand;

    @Optional @Part
    private MgCatchCommand catchCommand;

    @Optional @Part
    private MgFinallyCommand finallyCommand;

    public MgCheckpointCommand() {
    }

    public MgTryCommand getTryCommand() {
        return tryCommand;
    }

    public void setTryCommand(MgTryCommand tryCommand) {
        this.tryCommand = tryCommand;
    }

    public MgCatchCommand getCatchCommand() {
        return catchCommand;
    }

    public void setCatchCommand(MgCatchCommand catchCommand) {
        this.catchCommand = catchCommand;
    }

    public MgFinallyCommand getFinallyCommand() {
        return finallyCommand;
    }

    public void setFinallyCommand(MgFinallyCommand finallyCommand) {
        this.finallyCommand = finallyCommand;
    }
}
