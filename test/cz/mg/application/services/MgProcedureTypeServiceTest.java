package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.services.runtime.MgProcedureTypeService;
import cz.mg.collections.text.Text;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class MgProcedureTypeServiceTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgProcedureTypeServiceTest());
    }

    @TestCase
    public void testCreateProcedureType(){
        MgProcedure procedure = new MgProcedure(new Text("testProcedure"));
        // todo
        MgProcedureTypeService.create(procedure);
        assertNotNull(procedure.getType());
    }
}
