package cz.mg.application.entities.statical.components;

import cz.mg.application.entities.dynamical.MgType;
import cz.mg.application.entities.statical.MgComponent;
import cz.mg.collections.text.Text;


public abstract class MgDefinition extends MgComponent {
    public MgDefinition(Text name) {
        super(name);
    }

    public abstract MgType getType();
}
