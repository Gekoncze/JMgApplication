package cz.mg.application;

import cz.mg.application.architecture.MgCore;
import cz.mg.application.architecture.MgThread;
import cz.mg.application.architecture.utilities.JavaThread;
import cz.mg.application.entities.runtime.MgRuntimeCommand;
import cz.mg.application.entities.runtime.instructions.MgBuildinRunnableInstruction;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.application.entities.statical.parts.commands.MgExpressionCommand;
import cz.mg.application.services.MgProcedureTypeService;
import cz.mg.collections.array.Array;


public class RuntimeEnvironment {
    public static void run(Runnable runnable){
        MgCore core = new MgCore();
        MgThread thread = new MgThread();
        MgProcedure procedure = new MgProcedure();
        MgExpressionCommand command = new MgExpressionCommand();

        MgBuildinRunnableInstruction instruction = new MgBuildinRunnableInstruction(command, null, (task) -> {
            runnable.run();
            core.destroy();
        });

        command.setRuntimeCommand(new MgRuntimeCommand(
            command,
            null,
            new Array<>(instruction),
            new Array<>()
        ));

        procedure.getCommands().addLast(command);
        MgProcedureTypeService.create(procedure);
        thread.getStack().addLast(procedure.getType().create());
        core.setThread(thread);

        core.start();
        while(core.isAlive()) JavaThread.snooze();
    }
}
