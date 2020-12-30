package cz.mg.application.entities.statical.components;

import cz.mg.application.entities.runtime.types.MgType;


public abstract class MgDefinition extends MgComponent {
    public MgDefinition() {
        super();
    }

    public abstract MgType getType();
}
