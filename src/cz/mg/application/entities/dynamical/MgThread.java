package cz.mg.application.entities.dynamical;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public class MgThread {
    @Mandatory @Part
    private final List<MgTask> stack = new List<>();

    public MgThread() {
    }

    public List<MgTask> getStack() {
        return stack;
    }
}
