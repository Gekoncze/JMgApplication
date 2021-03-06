package cz.mg.application.entities.types;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Parent;
import cz.mg.application.entities.objects.MgAtomicObject;
import cz.mg.application.entities.components.definitions.MgAtom;
import cz.mg.collections.array.ReadonlyArray;


public class MgAtomicType extends MgType {
    @Mandatory @Parent
    private final MgAtom atom;

    public MgAtomicType(MgAtom atom) {
        super(new ReadonlyArray<>());
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
