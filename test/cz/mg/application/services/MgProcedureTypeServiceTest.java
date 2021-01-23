package cz.mg.application.services;

import cz.mg.application.entities.buildin.atoms.int32.MgInt32;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.commands.MgExpressionCommand;
import cz.mg.application.entities.statical.parts.commands.MgReturnCommand;
import cz.mg.application.entities.statical.parts.expressions.MgAssignmentExpression;
import cz.mg.application.entities.statical.parts.expressions.MgLocalVariableExpression;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.collections.text.Text;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class MgProcedureTypeServiceTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgProcedureTypeServiceTest());
    }

    private static MgProcedure createProcedure(String name){
        MgProcedure procedure = new MgProcedure();
        procedure.setName(new Text(name));
        return procedure;
    }

    private static MgInstanceVariable createVariable(String name){
        MgInstanceVariable variable = new MgInstanceVariable();
        variable.setName(new Text(name));
        variable.setDefinition(MgInt32.getInstance());
        return variable;
    }

    @TestCase
    public void testCreateProcedureType(){
//        MgProcedure procedure = createProcedure("testProcedure");
//        // todo
//        MgProcedureTypeService.create(procedure);
//        assertNotNull(procedure.getType());
    }

    @TestCase
    public void testCreateProcedureTypeHasVariablesInOrder(){
        MgInstanceVariable input = createVariable("input");
        MgInstanceVariable output = createVariable("output");
        MgInstanceVariable local = createVariable("local");
        MgProcedure procedure = createProcedure("testProcedure");
        procedure.getCommands().addLast(new MgExpressionCommand(
            new MgAssignmentExpression(
                new MgLocalVariableExpression(output),
                new MgLocalVariableExpression(input)
            )
        ));
        procedure.getCommands().addLast(new MgReturnCommand());
        procedure.getInput().addLast(input);
        procedure.getOutput().addLast(output);
        procedure.getLocal().addLast(local);
        MgProcedureTypeService.create(procedure);
        assertNotNull(procedure.getType());
        assertNotNull(procedure.getType().getInstanceVariables());
        assertEquals(3, procedure.getType().getInstanceVariables().count());
        assertEquals(input, procedure.getType().getInstanceVariables().get(0));
        assertEquals(output, procedure.getType().getInstanceVariables().get(1));
        assertEquals(local, procedure.getType().getInstanceVariables().get(2));
    }
}
