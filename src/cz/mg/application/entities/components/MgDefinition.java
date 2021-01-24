package cz.mg.application.entities.components;

import cz.mg.application.entities.types.MgType;


public abstract class MgDefinition extends MgComponent {
    public MgDefinition() {
        super();
    }

    public abstract MgType getType();
}
