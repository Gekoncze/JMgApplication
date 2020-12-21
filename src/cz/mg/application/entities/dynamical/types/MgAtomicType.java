package cz.mg.application.entities.dynamical.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Parent;
import cz.mg.application.entities.dynamical.objects.MgAtomicObject;
import cz.mg.application.entities.statical.components.definitions.MgAtom;


public class MgAtomicType extends MgType {
    @Mandatory @Parent
    private final MgAtom atom;

    public MgAtomicType(MgAtom atom) {
        this.atom = atom;
    }

    public MgAtom getAtom() {
        return atom;
    }

    @Override
    public MgAtomicObject create() {
        return atom.create();
    }
}
