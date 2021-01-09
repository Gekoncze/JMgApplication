package cz.mg.application.entities.statical.components.definitions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.runtime.types.MgClassType;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.variables.MgGlobalVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.variables.MgTypeVariable;
import cz.mg.collections.list.List;


public class MgClass extends MgDefinition {
    @Mandatory @Value
    private final Options options = new Options();

    @Mandatory @Link
    private final List<MgClass> baseClasses = new List<>();

    @Mandatory @Part
    private final List<MgInstanceVariable> instanceVariables = new List<>();

    @Mandatory @Part
    private final List<MgTypeVariable> typeVariables = new List<>();

    @Mandatory @Part
    private final List<MgGlobalVariable> globalVariables = new List<>();

    @Mandatory @Part
    private final List<MgProcedure> procedures = new List<>();

    @Mandatory @Part
    private final List<MgInterface> interfaces = new List<>();

    @Mandatory @Part
    private final List<MgOperator> operators = new List<>();

    @Optional @Cache
    private MgClassType type;

    public MgClass() {
    }

    public Options getOptions() {
        return options;
    }

    public List<MgClass> getBaseClasses() {
        return baseClasses;
    }

    public List<MgInstanceVariable> getInstanceVariables() {
        return instanceVariables;
    }

    public List<MgTypeVariable> getTypeVariables() {
        return typeVariables;
    }

    public List<MgGlobalVariable> getGlobalVariables() {
        return globalVariables;
    }

    public List<MgProcedure> getProcedures() {
        return procedures;
    }

    public List<MgInterface> getInterfaces() {
        return interfaces;
    }

    public List<MgOperator> getOperators() {
        return operators;
    }

    @Override
    public MgClassType getType() {
        return type;
    }

    public void setType(MgClassType type) {
        this.type = type;
    }

    public static class Options {
        @Mandatory @Value
        private boolean isAbstract = false;

        public Options() {
        }

        public boolean isAbstract() {
            return isAbstract;
        }

        public void setAbstract(boolean isAbstract) {
            this.isAbstract = isAbstract;
        }
    }
}
