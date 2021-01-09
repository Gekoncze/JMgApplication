package cz.mg.application.services;

import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.application.entities.statical.parts.variables.MgVariable;
import cz.mg.application.services.exceptions.ValidationException;


public class MgValidator extends MgService {
    public static void checkCompatibility(MgVariable source, MgDefinition destination)
    {
        if(!source.getDefinition().getType().is(destination.getType())){
            throw new ValidationException("Incompatible variable type: " + source.getDefinition().getType() + " is not " + destination.getName());
        }
    }

    public static void checkCompatibility(MgVariable source, MgVariable destination)
    {
        if(!source.getDefinition().getType().is(destination.getDefinition().getType())){
            throw new ValidationException("Incompatible variable types: " + source.getDefinition().getName() + " is not " + destination.getDefinition().getName() + ".");
        }
    }

//    public static void checkInputCompatibility(MgProcedure procedure, List<Connection> parameters){
//        if(procedure.get) todo;
//    }
}
