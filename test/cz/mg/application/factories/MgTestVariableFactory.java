package cz.mg.application.factories;

import cz.mg.application.entities.buildin.atoms.int32.MgInt32;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.application.entities.statical.parts.variables.MgGlobalVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.variables.MgInterfaceVariable;
import cz.mg.application.entities.statical.parts.variables.MgTypeVariable;
import cz.mg.collections.text.Text;


public interface MgTestVariableFactory {
    public static MgInstanceVariable createInstanceVariable(String name){
        return createInstanceVariable(
            name,
            MgInt32.getInstance()
        );
    }

    public static MgInstanceVariable createInstanceVariable(String name, MgDefinition definition){
        MgInstanceVariable variable = new MgInstanceVariable();
        variable.setName(new Text(name));
        variable.setDefinition(definition);
        return variable;
    }

    public static MgTypeVariable createTypeVariable(String name){
        return createTypeVariable(
            name,
            MgInt32.getInstance()
        );
    }

    public static MgTypeVariable createTypeVariable(String name, MgDefinition definition){
        MgTypeVariable variable = new MgTypeVariable();
        variable.setName(new Text(name));
        variable.setDefinition(definition);
        return variable;
    }

    public static MgGlobalVariable createGlobalVariable(String name){
        return createGlobalVariable(
            name,
            MgInt32.getInstance()
        );
    }

    public static MgGlobalVariable createGlobalVariable(String name, MgDefinition definition){
        MgGlobalVariable variable = new MgGlobalVariable();
        variable.setName(new Text(name));
        variable.setDefinition(definition);
        return variable;
    }

    public static MgInterfaceVariable createItnerfaceVariable(String name){
        return createItnerfaceVariable(
            name,
            MgInt32.getInstance()
        );
    }

    public static MgInterfaceVariable createItnerfaceVariable(String name, MgDefinition definition){
        MgInterfaceVariable variable = new MgInterfaceVariable();
        variable.setName(new Text(name));
        variable.setDefinition(definition);
        return variable;
    }
}
