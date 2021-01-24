package cz.mg.application.entities.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.components.definitions.MgAtom;
import cz.mg.collections.text.Text;


public abstract class MgValueExpression extends MgExpression {
    @Optional @Link
    private final MgAtom atom;

    @Optional @Value
    private Text value;

    public MgValueExpression(MgAtom atom) {
        this.atom = atom;
    }

    public MgAtom getAtom() {
        return atom;
    }

    public Text getValue() {
        return value;
    }

    public void setValue(Text value) {
        this.value = value;
    }
}
