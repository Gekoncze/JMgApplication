package cz.mg.application.entities.dynamical.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.dynamical.MgThread;
import cz.mg.application.entities.dynamical.MgType;
import cz.mg.application.entities.statical.parts.MgProcedure;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.array.Array;


public class MgProcedureType extends MgType {
    @Mandatory @Link
    private final MgProcedure procedure;

    public MgProcedureType(MgProcedure procedure) {
        super(
            new Array<>(),
            unionVariables(procedure),
            new Array<>(),
            new Array<>()
        );
        this.procedure = procedure;
    }

    public MgProcedure getProcedure() {
        return procedure;
    }

    public MgTask create(MgThread thread){
        return new MgTask(this, thread);
    }

    private static Array<MgVariable> unionVariables(MgProcedure procedure){
        Array<MgVariable> variables = new Array<>(
            procedure.getInput().count() +
            procedure.getOutput().count() +
            procedure.getLocal().count()
        );

        int i = 0;

        for(MgVariable variable : procedure.getInput()){
            variables.set(variable, i);
            i++;
        }

        for(MgVariable variable : procedure.getOutput()){
            variables.set(variable, i);
            i++;
        }

        for(MgVariable variable : procedure.getLocal()){
            variables.set(variable, i);
            i++;
        }

        return variables;
    }
}
