package cz.mg.application.entities.dynamical.types;

import cz.mg.application.entities.dynamical.instructions.MgInstruction;
import cz.mg.application.entities.dynamical.instructions.dummy.MgBeginDummyInstruction;
import cz.mg.application.entities.dynamical.instructions.dummy.MgEndDummyInstruction;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.commands.MgCommand;
import cz.mg.application.entities.statical.parts.commands.MgExpressionCommand;
import cz.mg.collections.array.Array;
import cz.mg.collections.text.Text;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class MgProcedureTypeTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgProcedureTypeTest());
    }

    @TestCase
    public void testCreate(){
        MgProcedure procedure = new MgProcedure();
        procedure.setName(new Text("fooBar"));

        MgInstruction expectedInstruction;

        MgCommand firstCommand = new MgExpressionCommand();
        firstCommand.setInstructions(new Array<>(
            expectedInstruction = new MgBeginDummyInstruction(firstCommand),
            new MgEndDummyInstruction(firstCommand)
        ));

        MgCommand secondCommand = new MgExpressionCommand();
        secondCommand.setInstructions(new Array<>(
            new MgBeginDummyInstruction(secondCommand),
            new MgEndDummyInstruction(secondCommand)
        ));

        procedure.getCommands().addLast(firstCommand);
        procedure.getCommands().addLast(secondCommand);

        MgProcedureType procedureType = new MgProcedureType(
            procedure,
            new Array<>()
        );

        MgTask task = procedureType.create();
        assertEquals(procedureType, task.getType());
        assertEquals(expectedInstruction, task.getInstruction());
    }
}
