package cz.mg.application.services;

import cz.mg.application.entities.runtime.types.MgType;
import cz.mg.application.entities.runtime.types.MgClassType;
import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.components.definitions.MgOperator;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;


public class MgClassTypeService extends MgService {
    public static void create(MgClass clazz){
        validate(clazz);
        Array<MgType> types = unionTypes(clazz);
        Array<MgVariable> variables = unionVariables(clazz);
        Array<MgProcedure> procedures = unionProcedures(clazz);
        Array<MgInterface> interfaces = unionInterfaces(clazz);
        Array<MgOperator> operators = unionOperators(clazz);
        Map<MgInterface, MgProcedure> procedureMap = createProcedureMap(clazz, interfaces);
        clazz.setType(new MgClassType(clazz, types, variables, procedures, interfaces, operators, procedureMap));
    }

    private static Array<MgType> unionTypes(MgClass clazz){
        List<MgType> types = new List<>();
        for(MgClass base : clazz.getBaseClasses()){
            if(base.getType() == null) create(base);
            types.addLast(base.getType());
        }
        return new Array<>(types);
    }

    private static void validate(MgClass clazz){
        // check for cyclic inheritance
        MgClassService.forEachClass(clazz, c -> {});
    }

    private static Array<MgVariable> unionVariables(MgClass clazz){
        List<MgVariable> variables = new List<>();
        for(MgClass base : clazz.getBaseClasses()){
            for(MgVariable variable : base.getType().getVariables()){
                if(!variables.contains(variable)){
                    variables.addLast(variable);
                }
            }
        }
        variables.addCollectionLast(clazz.getVariables());
        return new Array<>(variables);
    }

    private static Array<MgProcedure> unionProcedures(MgClass clazz){
        List<MgProcedure> procedures = new List<>();
        for(MgClass base : clazz.getBaseClasses()){
            for(MgProcedure procedure : base.getType().getProcedures()){
                if(!procedures.contains(procedure)){
                    procedures.addLast(procedure);
                }
            }
        }
        procedures.addCollectionLast(clazz.getProcedures());
        return new Array<>(procedures);
    }

    private static Array<MgInterface> unionInterfaces(MgClass clazz){
        List<MgInterface> interfaces = new List<>();
        for(MgClass base : clazz.getBaseClasses()){
            for(MgInterface mgInterface : base.getType().getInterfaces()){
                if(!interfaces.contains(mgInterface)){
                    interfaces.addLast(mgInterface);
                }
            }
        }
        interfaces.addCollectionLast(clazz.getInterfaces());
        return new Array<>(interfaces);
    }

    private static Array<MgOperator> unionOperators(MgClass clazz){
        List<MgOperator> operators = new List<>();
        for(MgClass base : clazz.getBaseClasses()){
            for(MgOperator operaotor : base.getType().getOperators()){
                if(!operators.contains(operaotor)){
                    operators.addLast(operaotor);
                }
            }
        }
        operators.addCollectionLast(clazz.getOperators());
        return new Array<>(operators);
    }

    private static Map<MgInterface, MgProcedure> createProcedureMap(MgClass clazz, Array<MgInterface> interfaces){
        Map<MgInterface, MgProcedure> map = new Map<>();
        for(MgInterface mgInterface : interfaces){
            map.set(mgInterface, MgClassTypeInterfaceService.resolveProcedure(clazz, mgInterface));
        }
        return map;
    }
}
