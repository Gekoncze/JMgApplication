package cz.mg.application.entities.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Parent;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.objects.MgTask;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.entities.parts.MgCheckpoint;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.map.Map;


public class MgProcedureType extends MgStructuredType {
    @Mandatory @Parent
    private final MgProcedure procedure;

    @Mandatory @Part
    private final ReadonlyArray<MgInstruction> instructions;

    @Mandatory @Link
    private final Map<MgCheckpoint, MgInstruction> checkpointInstructionMap;

    @Mandatory @Cache
    private final int inputDelta;


    @Mandatory @Cache
    private final int outputDelta;

    public MgProcedureType(
        MgProcedure procedure,
        ReadonlyArray<MgInstanceVariable> instanceVariables,
        ReadonlyArray<MgInstruction> instructions,
        Map<MgCheckpoint, MgInstruction> checkpointInstructionMap
    ) {
        super(new ReadonlyArray<>(), instanceVariables);
        this.instructions = instructions;
        this.procedure = procedure;
        this.checkpointInstructionMap = checkpointInstructionMap;
        this.inputDelta = 0;
        this.outputDelta = procedure.getInput().count();
    }

    public MgProcedure getProcedure() {
        return procedure;
    }

    public ReadonlyArray<MgInstruction> getInstructions() {
        return instructions;
    }

    public Map<MgCheckpoint, MgInstruction> getCheckpointInstructionMap() {
        return checkpointInstructionMap;
    }

    public int getInputDelta() {
        return inputDelta;
    }

    public int getOutputDelta() {
        return outputDelta;
    }

    @Override
    public MgTask create(){
        return new MgTask(this, getInstructions().getFirst());
    }
}
