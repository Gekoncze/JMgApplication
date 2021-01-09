package cz.mg.application.entities.statical.parts.variables;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.application.Named;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.application.entities.statical.parts.MgPart;
import cz.mg.collections.text.Text;


public abstract class MgVariable extends MgPart implements Named {
    @Mandatory @Value
    private Text name = new Text();

    @Optional @Link
    private MgDefinition definition;

    public MgVariable() {
    }

    @Override
    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public MgDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(MgDefinition definition) {
        this.definition = definition;
    }
}
