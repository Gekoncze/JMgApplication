package cz.mg.application;

import cz.mg.application.entities.architecture.MgSimpleRuntimeTest;
import cz.mg.application.entities.types.MgClassTypeTest;
import cz.mg.application.entities.types.MgTypeTest;
import cz.mg.application.services.MgClassServiceTest;
import cz.mg.application.services.MgClassTypeServiceTest;
import cz.mg.application.services.MgProcedureTypeServiceTest;
import cz.mg.application.services.MgValidatorTest;
import cz.mg.test.runner.BulkTestRunner;


public class MgAllTests {
    public static void main(String[] args) {
        BulkTestRunner testRunner = new BulkTestRunner();
        testRunner.run(
            new MgTypeTest(),
            new MgClassTypeTest(),
            new MgClassServiceTest(),
            new MgClassTypeServiceTest(),
            new MgProcedureTypeServiceTest(),
            new MgSimpleRuntimeTest(),
            new MgValidatorTest()
        );
    }
}
