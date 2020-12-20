package cz.mg.application.entities.statical.components.definitions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.dynamical.objects.MgAtomicObject;
import cz.mg.application.entities.dynamical.types.MgAtomicType;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public abstract class MgAtom extends MgDefinition {
    @Mandatory @Cache
    private final MgAtomicType type;

    @Mandatory @Part
    private final List<MgProcedure> procedures = new List<>();

    public MgAtom(Text name) {
        super(name);
        this.type = new MgAtomicType(this);
    }

    @Override
    public MgAtomicType getType() {
        return type;
    }

    public List<MgProcedure> getProcedures() {
        return procedures;
    }

    public abstract MgAtomicObject create();
}
