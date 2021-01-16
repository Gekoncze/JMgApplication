package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgProcedure;
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
        procedure.getInput().addLast(input);
        procedure.getOutput().addLast(output);
        procedure.getLocal().addLast(local);
        MgProcedureTypeService.create(procedure);
        assertNotNull(procedure.getType());
        assertNotNull(procedure.getType().getInstanceVariables());
        assertEquals(procedure.getType().getInstanceVariables().count(), 3);
        assertEquals(procedure.getType().getInstanceVariables().get(0), input);
        assertEquals(procedure.getType().getInstanceVariables().get(1), output);
        assertEquals(procedure.getType().getInstanceVariables().get(2), local);
    }
}
