package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.collections.array.ReadonlyArray;


public class MgDestroyTaskInstruction extends MgLinearInstruction {
    @Mandatory @Part
    private final ReadonlyArray<MgInstanceVariable> destinations;

    public MgDestroyTaskInstruction(ReadonlyArray<MgInstanceVariable> destinations) {
        this.destinations = destinations;
    }

    public ReadonlyArray<MgInstanceVariable> getDestinations() {
        return destinations;
    }

    @Override
    public void run(MgTask task) {
        MgThread thread = MgThread.getInstance();
        MgTask oldTask = thread.getStack().removeLast();
        task = thread.getStack().getLast();
        int i = oldTask.getType().getOutputDelta();
        for(MgInstanceVariable destination : destinations){
            task.setObject(destination, oldTask.getObject(i));
            i++;
        }
        super.run(task);
    }
}
