package cz.mg.application;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.application.architecture.MgCore;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.architecture.utilities.JavaThread;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.runtime.types.MgProcedureType;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.map.Map;


public class RuntimeEnvironment {
    private final MgCore core = new MgCore();
    private final MgThread thread = new MgThread();
    private final MgProcedure procedure = new MgProcedure();
    private final MgRunnableInstruction instruction = new MgRunnableInstruction();

    public RuntimeEnvironment() {
        procedure.setType(new MgProcedureType(
            procedure,
            new ReadonlyArray<>(),
            new ReadonlyArray<>(instruction),
            new Map<>()
        ));

        thread.getStack().addLast(procedure.getType().create());
        thread.setTask(thread.getStack().getLast());
        core.setThread(thread);
    }

    public MgCore getCore() {
        return core;
    }

    public MgThread getThread() {
        return thread;
    }

    public void run(@Mandatory Runnable runnable){
        instruction.setRunnable(runnable);
        core.start();
        while(core.isRunning()) JavaThread.snooze();
    }

    private class MgRunnableInstruction extends MgInstruction {
        @Mandatory @Part
        private Runnable runnable;

        public MgRunnableInstruction() {
        }

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run(MgTask task) {
            runnable.run();
            core.stop();
        }
    }
}
