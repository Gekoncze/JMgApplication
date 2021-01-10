package cz.mg.application.services.validation;

import cz.mg.application.entities.runtime.types.MgStructuredType;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.variables.MgVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.collections.ReadableCollection;

import java.util.Iterator;


public class MgValidator extends MgService {
    public static void checkOwnership(MgDefinition definition, MgVariable variable){
        if(definition.getType() instanceof MgStructuredType){
            MgStructuredType structuredType = (MgStructuredType) definition.getType();
            if(!structuredType.getVariables().contains(variable)){
                throw new ValidationException("Component " + definition.getName() + " does not have given variable.");
            }
        } else {
            throw new ValidationException("Component " + definition.getName() + " is not a structured type.");
        }
    }

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

    public static void checkInputCompatibility(MgProcedure procedure, ReadableCollection<MgInstanceVariable> localSource){
        if(procedure.getInput().count() != localSource.count()) {
            throw new ValidationException("Illegal input count for procedure " + procedure.getName() + ": " + procedure.getInput().count() + " vs " + localSource.count() + ".");
        }

        Iterator<MgInstanceVariable> sources = localSource.iterator();
        Iterator<MgInstanceVariable> destinations = procedure.getInput().iterator();
        while (sources.hasNext() && destinations.hasNext()){
            MgInstanceVariable source = sources.next();
            MgInstanceVariable destination = destinations.next();
            checkCompatibility(source, destination);
        }
    }

    public static void checkOutputCompatibility(MgProcedure procedure, ReadableCollection<MgInstanceVariable> localDestination){
        if(procedure.getOutput().count() != localDestination.count()) {
            throw new ValidationException("Illegal output count for procedure " + procedure.getName() + ": " + procedure.getOutput().count() + " vs " + localDestination.count() + ".");
        }

        Iterator<MgInstanceVariable> sources = procedure.getOutput().iterator();
        Iterator<MgInstanceVariable> destinations = localDestination.iterator();
        while (sources.hasNext() && destinations.hasNext()){
            MgInstanceVariable source = sources.next();
            MgInstanceVariable destination = destinations.next();
            checkCompatibility(source, destination);
        }
    }
}
