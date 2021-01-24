package cz.mg.application.factories;

import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.variables.MgGlobalVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.variables.MgTypeVariable;
import cz.mg.collections.Clump;
import cz.mg.collections.array.Array;
import cz.mg.collections.text.Text;


public interface MgTestClassFactory {
    public static MgClass createClass(String name){
        MgClass clazz = new MgClass();
        clazz.setName(new Text(name));
        return clazz;
    }

    public static MgClass createClass(String name, MgClass... baseClasses){
        return createClass(
            name,
            new Array<>(baseClasses),
            new Array<>(),
            new Array<>()
        );
    }

    public static MgClass createClass(
        String name,
        Clump<MgClass> baseClasses,
        Clump<MgInstanceVariable> variables,
        Clump<MgProcedure> procedures
    ){
        return createClass(
            name,
            baseClasses,
            new Array<>(),
            new Array<>(),
            variables,
            procedures
        );
    }

    public static MgClass createClass(
        String name,
        Clump<MgClass> baseClasses,
        Clump<MgGlobalVariable> globalVariables,
        Clump<MgTypeVariable> typeVariables,
        Clump<MgInstanceVariable> instanceVariables,
        Clump<MgProcedure> procedures
    ){
        MgClass clazz = new MgClass();
        clazz.setName(new Text(name));
        clazz.getBaseClasses().addCollectionLast(baseClasses);
        clazz.getGlobalVariables().addCollectionLast(globalVariables);
        clazz.getTypeVariables().addCollectionLast(typeVariables);
        clazz.getInstanceVariables().addCollectionLast(instanceVariables);
        clazz.getProcedures().addCollectionLast(procedures);
        return clazz;
    }
}
