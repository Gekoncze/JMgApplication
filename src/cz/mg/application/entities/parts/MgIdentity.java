package cz.mg.application.entities.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.text.Text;


public class MgIdentity extends MgPart {
    @Mandatory @Value
    private final Text uid;

    @Mandatory @Value
    private final MgVersion version;

    public MgIdentity(Text uid, MgVersion version) {
        this.uid = uid;
        this.version = version;
    }

    public Text getUid() {
        return uid;
    }

    public MgVersion getVersion() {
        return version;
    }

    public static boolean isCompatible(MgIdentity actual, MgIdentity required) {
        return actual.uid == required.uid
            && MgVersion.isCompatible(actual.version, required.version);
    }
}
