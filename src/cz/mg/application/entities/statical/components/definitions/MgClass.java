package cz.mg.application.entities.statical.components.definitions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.dynamical.types.MgClassType;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public class MgClass extends MgDefinition {
    @Mandatory @Value
    private final Options options = new Options();

    @Mandatory @Link
    private final List<MgClass> baseClasses = new List<>();

    @Mandatory @Part
    private final List<MgVariable> variables = new List<>();

    @Mandatory @Part
    private final List<MgProcedure> procedures = new List<>();

    @Mandatory @Part
    private final List<MgInterface> interfaces = new List<>();

    @Optional @Cache
    private MgClassType type;

    public MgClass(Text name) {
        super(name);
    }

    public Options getOptions() {
        return options;
    }

    public List<MgClass> getBaseClasses() {
        return baseClasses;
    }

    public List<MgVariable> getVariables() {
        return variables;
    }

    public List<MgProcedure> getProcedures() {
        return procedures;
    }

    public List<MgInterface> getInterfaces() {
        return interfaces;
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
