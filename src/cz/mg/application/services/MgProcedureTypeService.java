package cz.mg.application.services;

import cz.mg.application.entities.dynamical.types.MgProcedureType;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.array.Array;


public class MgProcedureTypeService extends MgService {
    public static void create(MgProcedure procedure){
        // todo - create instructions from commands
        procedure.setType(new MgProcedureType(procedure, unionVariables(procedure)));
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
