package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.statical.components.definitions.MgAtom;


public abstract class MgValueExpression extends MgExpression {
    @Optional @Link
    private final MgAtom atom;

    @Optional @Value
    private Object value;

    public MgValueExpression(MgAtom atom) {
        this.atom = atom;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
