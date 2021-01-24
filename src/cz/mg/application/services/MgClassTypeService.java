package cz.mg.application.services;

import cz.mg.application.entities.types.MgClassType;
import cz.mg.application.entities.types.MgType;
import cz.mg.application.entities.components.definitions.MgClass;
import cz.mg.application.entities.components.definitions.MgOperator;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.entities.parts.MgInterface;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;

import static cz.mg.application.services.MgClassService.forEachClass;


public class MgClassTypeService extends MgService {
    public static void create(MgClass clazz){
        if(clazz.getType() != null) return;
        validate(clazz);
        createDependentTypes(clazz);
        ReadonlyArray<MgType> types = unionTypes(clazz);
        ReadonlyArray<MgInstanceVariable> instanceVariables = unionVariables(clazz);
        ReadonlyArray<MgProcedure> procedures = unionProcedures(clazz);
        ReadonlyArray<MgInterface> interfaces = unionInterfaces(clazz);
        ReadonlyArray<MgOperator> operators = unionOperators(clazz);
        Map<MgInterface, MgProcedure> procedureMap = createProcedureMap(clazz, interfaces);
        clazz.setType(new MgClassType(clazz, types, instanceVariables, procedures, interfaces, operators, procedureMap));
    }

    private static void validate(MgClass clazz){
        // check for cyclic inheritance
        forEachClass(clazz, c -> {});
    }

    private static void createDependentTypes(MgClass clazz){
        for(MgClass base : clazz.getBaseClasses()){
            create(base);
        }
    }

    private static ReadonlyArray<MgType> unionTypes(MgClass clazz){
        List<MgType> types = new List<>();
        forEachClass(clazz, (c -> types.addLast(c.getType())));
        return new ReadonlyArray<>(types);
    }

    private static ReadonlyArray<MgInstanceVariable> unionVariables(MgClass clazz){
        List<MgInstanceVariable> variables = new List<>();
        forEachClass(clazz, (c -> variables.addCollectionLast(c.getInstanceVariables())));
        return new ReadonlyArray<>(variables);
    }

    private static ReadonlyArray<MgProcedure> unionProcedures(MgClass clazz){
        List<MgProcedure> procedures = new List<>();
        forEachClass(clazz, (c -> procedures.addCollectionLast(c.getProcedures())));
        return new ReadonlyArray<>(procedures);
    }

    private static ReadonlyArray<MgInterface> unionInterfaces(MgClass clazz){
        List<MgInterface> interfaces = new List<>();
        forEachClass(clazz, (c -> interfaces.addCollectionLast(c.getInterfaces())));
        return new ReadonlyArray<>(interfaces);
    }

    private static ReadonlyArray<MgOperator> unionOperators(MgClass clazz){
        List<MgOperator> operators = new List<>();
        forEachClass(clazz, (c -> operators.addCollectionLast(c.getOperators())));
        return new ReadonlyArray<>(operators);
    }

    private static Map<MgInterface, MgProcedure> createProcedureMap(MgClass clazz, ReadonlyArray<MgInterface> interfaces){
        Map<MgInterface, MgProcedure> map = new Map<>();
        for(MgInterface mgInterface : interfaces){
            map.set(mgInterface, MgClassTypeInterfaceService.resolveProcedure(clazz, mgInterface));
        }
        return map;
    }
}
