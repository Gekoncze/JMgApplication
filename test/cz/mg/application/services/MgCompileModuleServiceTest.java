package cz.mg.application.services;

import cz.mg.application.entities.architecture.MgModule;
import cz.mg.application.entities.components.MgLocation;
import cz.mg.application.entities.components.definitions.MgClass;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;

import static cz.mg.application.factories.MgTestClassFactory.createClass;
import static cz.mg.application.factories.MgTestLocationFactory.createLocation;
import static cz.mg.application.factories.MgTestModuleFactory.createModule;
import static cz.mg.application.factories.MgTestProcedureFactory.createProcedure;


public class MgCompileModuleServiceTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgCompileModuleServiceTest());
    }

    @TestCase(order = 0)
    public void testCompile(){
        MgLocation location = createLocation("location");
        MgModule module = createModule("test");
        module.getRoot().getComponents().addLast(location);
        MgClass clazz = createClass("TestClass");
        MgProcedure outerProcedure = createProcedure("outerProcedure");
        MgProcedure innerProcedure = createProcedure("innerProcedure");
        clazz.getProcedures().addLast(innerProcedure);
        location.getComponents().addLast(outerProcedure);
        location.getComponents().addLast(clazz);

        MgCompileModuleService.compile(module);

        assertNotNull(clazz.getType());
        assertNotNull(outerProcedure.getType());
        assertNotNull(innerProcedure.getType());
    }
}
