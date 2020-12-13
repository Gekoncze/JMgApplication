package cz.mg.application.entities.statical.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.Named;
import cz.mg.application.entities.statical.MgPart;
import cz.mg.application.entities.statical.components.MgClass;
import cz.mg.collections.text.Text;


public class MgVariable extends MgPart implements Named {
    @Mandatory @Value
    private final int index;

    @Mandatory @Link
    private final MgClass type;

    @Mandatory @Value
    private final Text name;

    public MgVariable(int index, MgClass type, Text name) {
        this.index = index;
        this.type = type;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public MgClass getType() {
        return type;
    }

    @Override
    public Text getName() {
        return name;
    }
}
