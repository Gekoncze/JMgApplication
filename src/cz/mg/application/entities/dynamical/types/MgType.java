package cz.mg.application.entities.dynamical.types;

import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.dynamical.objects.MgObject;


public abstract class MgType extends MgEntity {
    public MgType() {
    }

    public abstract MgObject create();
}
