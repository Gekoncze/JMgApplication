package cz.mg.application.entities.runtime.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Parent;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.MgCheckpoint;
import cz.mg.application.entities.statical.parts.variables.MgVariable;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.map.Map;


public class MgProcedureType extends MgStructuredType {
    @Mandatory @Parent
    private final MgProcedure procedure;

    @Mandatory @Part
    private final ReadableArray<MgInstruction> instructions;

    @Mandatory @Link
    private final Map<MgCheckpoint, MgInstruction> checkpointInstructionMap;

    public MgProcedureType(
        MgProcedure procedure,
        ReadableArray<MgVariable> variables,
        ReadableArray<MgInstruction> instructions,
        Map<MgCheckpoint, MgInstruction> checkpointInstructionMap
    ) {
        super(new Array<>(), variables);
        this.instructions = instructions;
        this.procedure = procedure;
        this.checkpointInstructionMap = checkpointInstructionMap;
    }

    public MgProcedure getProcedure() {
        return procedure;
    }

    public ReadableArray<MgInstruction> getInstructions() {
        return instructions;
    }

    public Map<MgCheckpoint, MgInstruction> getCheckpointInstructionMap() {
        return checkpointInstructionMap;
    }

    @Override
    public MgTask create(){
        return new MgTask(this, getInstructions().getFirst());
    }
}
