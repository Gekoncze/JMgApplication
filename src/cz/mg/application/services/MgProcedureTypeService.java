package cz.mg.application.services;

import cz.mg.application.entities.runtime.types.MgProcedureType;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.map.Map;


public class MgProcedureTypeService extends MgService {
    public static void create(MgProcedure procedure){
        // todo - add instruction validation
        procedure.setType(new MgProcedureType(
            procedure,
            unionVariables(procedure),
            new ReadonlyArray<>(), // todo - create instructions from commands
            new Map<>() // todo
        ));
    }

    private static ReadonlyArray<MgInstanceVariable> unionVariables(MgProcedure procedure){
        Array<MgInstanceVariable> variables = new Array<>(
            procedure.getInput().count() +
                procedure.getOutput().count() +
                procedure.getLocal().count()
        );

        int i = 0;

        for(MgInstanceVariable variable : procedure.getInput()){
            variables.set(variable, i);
            i++;
        }

        for(MgInstanceVariable variable : procedure.getOutput()){
            variables.set(variable, i);
            i++;
        }

        for(MgInstanceVariable variable : procedure.getLocal()){
            variables.set(variable, i);
            i++;
        }

        return new ReadonlyArray<>(variables);
    }
}
