package cz.mg.application.entities.statical.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.application.Named;
import cz.mg.application.entities.statical.MgPart;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.collections.text.Text;


public class MgVariable extends MgPart implements Named {
    @Mandatory @Link
    private final MgDefinition definition;

    @Mandatory @Value
    private final Text name;

    public MgVariable(MgDefinition definition, Text name) {
        this.definition = definition;
        this.name = name;
    }

    public MgDefinition getDefinition() {
        return definition;
    }

    @Override
    public Text getName() {
        return name;
    }
}
