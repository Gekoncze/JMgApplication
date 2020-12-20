package cz.mg.application;

import cz.mg.application.services.MgClassTypeServiceTest;
import cz.mg.application.services.MgProcedureTypeServiceTest;
import cz.mg.test.runner.BulkTestRunner;


public class MgAllTests {
    public static void main(String[] args) {
        BulkTestRunner testRunner = new BulkTestRunner();
        testRunner.run(
            new MgClassTypeServiceTest(),
            new MgProcedureTypeServiceTest()
        );
    }
}
