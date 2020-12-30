package cz.mg.application.entities.runtime.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Parent;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;


public class MgProcedureType extends MgStructuredType {
    @Mandatory @Parent
    private final MgProcedure procedure;

    public MgProcedureType(
        MgProcedure procedure,
        ReadableArray<MgVariable> variables
    ) {
        super(new Array<>(), variables);
        this.procedure = procedure;
    }

    public MgProcedure getProcedure() {
        return procedure;
    }

    @Override
    public MgTask create(){
        return new MgTask(
            this,
            procedure.getCommands().getFirst().getRuntimeCommand().getInstructions().getFirst()
        );
    }
}