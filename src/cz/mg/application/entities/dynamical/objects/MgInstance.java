package cz.mg.application.entities.dynamical.objects;

import cz.mg.application.entities.dynamical.MgObject;
import cz.mg.application.entities.dynamical.types.MgClassType;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.MgProcedure;
import cz.mg.collections.map.Map;


public class MgInstance extends MgObject {
    public MgInstance(MgClassType type, Map<MgInterface, MgProcedure> procedures) {
        super(type, procedures);
    }

    @Override
    public MgClassType getType() {
        return (MgClassType) super.getType();
    }
}
