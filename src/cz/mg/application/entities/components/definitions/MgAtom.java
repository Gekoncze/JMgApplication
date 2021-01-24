package cz.mg.application.entities.components.definitions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.objects.MgAtomicObject;
import cz.mg.application.entities.types.MgAtomicType;
import cz.mg.application.entities.components.MgDefinition;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;


public abstract class MgAtom extends MgDefinition {
    @Mandatory @Cache
    private final MgAtomicType type;

    @Mandatory @Part
    private final List<MgProcedure> procedures = new List<>();

    @Mandatory @Part
    private final List<MgOperator> operators = new List<>();

    public MgAtom() {
        this.type = new MgAtomicType(this);
    }

    @Override
    public MgAtomicType getType() {
        return type;
    }

    public List<MgProcedure> getProcedures() {
        return procedures;
    }

    public List<MgOperator> getOperators() {
        return operators;
    }

    public abstract MgAtomicObject create();
    public abstract Text toText(MgAtomicObject atom);
    public abstract MgAtomicObject fromText(Text text);
}
