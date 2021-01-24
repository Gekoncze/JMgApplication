package cz.mg.application.services.validation;

import cz.mg.application.entities.types.MgStructuredType;
import cz.mg.application.entities.components.MgDefinition;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.entities.parts.MgInterface;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.parts.variables.MgInterfaceVariable;
import cz.mg.application.entities.parts.variables.MgVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.ValidationException;
import cz.mg.collections.Clump;
import cz.mg.collections.ReadableCollection;

import java.util.Iterator;


public class MgValidator extends MgService {
    public static void checkNotNull(Object object){
        if(object == null){
            throw new ValidationException("Missing object.");
        }
    }

    public interface ValidationLogic<Type> {
        void validate(Type type);
    }

    public static <Type> void checkType(Object object, Class<Type> typeClass, ValidationLogic<Type> validationLogic){
        if(typeClass.isInstance(object)){
            validationLogic.validate((Type) object);
        } else {
            throw new ValidationException("Expected " + typeClass.getSimpleName() + ".");
        }
    }

    public static void checkOwnership(MgDefinition definition, Clump<MgInstanceVariable> variables){
        for(MgInstanceVariable variable : variables){
            checkOwnership(definition, variable);
        }
    }

    public static void checkOwnership(MgDefinition definition, MgInstanceVariable variable){
        checkType(definition.getType(), MgStructuredType.class, structuredType -> {
            if(!structuredType.getInstanceVariables().contains(variable)){
                throw new ValidationException("Type " + definition.getName() + " does not contain variable " + variable.getName() + ".");
            }
        });
    }

    public static void checkCompatibility(MgVariable source, MgDefinition destination)
    {
        if(!source.getDefinition().getType().is(destination.getType())){
            throw new ValidationException("Incompatible types: " + source.getDefinition().getType() + " is not " + destination.getName());
        }
    }

    public static void checkCompatibility(MgVariable source, MgVariable destination)
    {
        if(!source.getDefinition().getType().is(destination.getDefinition().getType())){
            throw new ValidationException("Incompatible types: " + source.getDefinition().getName() + " is not " + destination.getDefinition().getName() + ".");
        }
    }

    public static void checkCompatibility(MgDefinition source, MgVariable destination)
    {
        if(!source.getType().is(destination.getDefinition().getType())){
            throw new ValidationException("Incompatible types: " + source.getName() + " is not " + destination.getDefinition().getName() + ".");
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

    public static void checkInputCompatibility(MgInterface iinterface, ReadableCollection<MgInstanceVariable> localSource){
        if(iinterface.getInput().count() != localSource.count()) {
            throw new ValidationException("Illegal input count for procedure " + iinterface.getName() + ": " + iinterface.getInput().count() + " vs " + localSource.count() + ".");
        }

        Iterator<MgInstanceVariable> sources = localSource.iterator();
        Iterator<MgInterfaceVariable> destinations = iinterface.getInput().iterator();
        while (sources.hasNext() && destinations.hasNext()){
            MgInstanceVariable source = sources.next();
            MgInterfaceVariable destination = destinations.next();
            checkCompatibility(source, destination);
        }
    }

    public static void checkOutputCompatibility(MgInterface iinterface, ReadableCollection<MgInstanceVariable> localDestination){
        if(iinterface.getOutput().count() != localDestination.count()) {
            throw new ValidationException("Illegal output count for procedure " + iinterface.getName() + ": " + iinterface.getOutput().count() + " vs " + localDestination.count() + ".");
        }

        Iterator<MgInterfaceVariable> sources = iinterface.getOutput().iterator();
        Iterator<MgInstanceVariable> destinations = localDestination.iterator();
        while (sources.hasNext() && destinations.hasNext()){
            MgInterfaceVariable source = sources.next();
            MgInstanceVariable destination = destinations.next();
            checkCompatibility(source, destination);
        }
    }
}
