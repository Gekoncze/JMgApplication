package cz.mg.application.entities.statical.parts;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.statical.MgPart;


public class MgVersion extends MgPart {
    @Mandatory @Value
    private final int major;

    @Mandatory @Value
    private final int minor;

    @Mandatory @Value
    private final int patch;

    public MgVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    public static boolean isCompatible(MgVersion actual, MgVersion required) {
        return actual.major == required.major
            && actual.minor >= required.minor;
    }
}
