package cz.mg.application;

import cz.mg.application.architecture.MgCore;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.architecture.utilities.JavaThread;
import cz.mg.application.entities.runtime.instructions.MgBuildinRunnableInstruction;
import cz.mg.application.entities.runtime.types.MgProcedureType;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.collections.array.Array;
import cz.mg.collections.map.Map;


public class RuntimeEnvironment {
    public static void run(Runnable runnable){
        MgCore core = new MgCore();
        MgThread thread = new MgThread();
        MgProcedure procedure = new MgProcedure();

        MgBuildinRunnableInstruction instruction = new MgBuildinRunnableInstruction(null, (task) -> {
            runnable.run();
            core.destroy();
        });

        procedure.setType(new MgProcedureType(
            procedure,
            new Array<>(),
            new Array<>(instruction),
            new Map<>()
        ));

        thread.getStack().addLast(procedure.getType().create());
        core.setThread(thread);

        core.start();
        while(core.isAlive()) JavaThread.snooze();
    }
}
