package cz.mg.application.entities.dynamical;

import cz.mg.application.entities.MgEntity;


public abstract class MgType extends MgEntity {
    public MgType() {
    }

    public abstract MgObject create();
}
