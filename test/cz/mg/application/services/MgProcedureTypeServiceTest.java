package cz.mg.application.services;

import cz.mg.application.entities.buildin.atoms.float32.MgFloat32;
import cz.mg.application.entities.buildin.atoms.int32.MgInt32;
import cz.mg.application.entities.statical.components.definitions.MgBinaryOperator;
import cz.mg.application.entities.statical.components.definitions.MgLunaryOperator;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;

import static cz.mg.application.factories.MgTestInterfaceFactory.createInterface;
import static cz.mg.application.factories.MgTestOperatorFactory.*;
import static cz.mg.application.factories.MgTestProcedureFactory.createProcedure;
import static cz.mg.application.factories.MgTestVariableFactory.createInstanceVariable;
import static cz.mg.application.factories.MgTestVariableFactory.createItnerfaceVariable;


public class MgProcedureTypeServiceTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgProcedureTypeServiceTest());
    }

    @TestCase
    public void testCreate(){
        MgProcedure procedure = createProcedure("testProcedure");
        MgProcedureTypeService.create(procedure);
        assertNotNull(procedure.getType());
    }

    @TestCase
    public void testCreateBinaryOperator(){
        MgBinaryOperator operator = createBinaryOperator(
            "testBinaryOperator",
            createInstanceVariable("leftInput", MgInt32.getInstance()),
            createInstanceVariable("rightInput", MgInt32.getInstance()),
            createInstanceVariable("output", MgInt32.getInstance())
        );
        MgProcedureTypeService.create(operator);
        assertNotNull(operator.getType());
    }

    @TestCase
    public void testCreateIncompleteBinaryOperator(){
        MgBinaryOperator operator = createBinaryOperator("testBinaryOperator");
        assertExceptionThrown(() -> {
            MgProcedureTypeService.create(operator);
        });
    }

    @TestCase
    public void testCreateLunaryOperator(){
        MgLunaryOperator operator = createLunaryOperator(
            "testLunaryOperator",
            createInstanceVariable("input", MgInt32.getInstance()),
            createInstanceVariable("output", MgInt32.getInstance())
        );
        MgProcedureTypeService.create(operator);
        assertNotNull(operator.getType());
    }

    @TestCase
    public void testCreateIncompleteLunaryOperator(){
        MgLunaryOperator operator = createLunaryOperator("testLunaryOperator");
        assertExceptionThrown(() -> {
            MgProcedureTypeService.create(operator);
        });
    }

    @TestCase
    public void testCreateRunaryOperator(){
        MgRunaryOperator operator = createRunaryOperator(
            "testRunaryOperator",
            createInstanceVariable("input", MgInt32.getInstance()),
            createInstanceVariable("output", MgInt32.getInstance())
        );
        MgProcedureTypeService.create(operator);
        assertNotNull(operator.getType());
    }

    @TestCase
    public void testCreateIncompleteRunaryOperator(){
        MgRunaryOperator operator = createRunaryOperator("testRunaryOperator");
        assertExceptionThrown(() -> {
            MgProcedureTypeService.create(operator);
        });
    }

    @TestCase
    public void testInterfaceCompatibilityEmpty(){
        MgInterface interfase = createInterface("emptyInterface");
        MgProcedure procedure = createProcedure("emptyProcedure");
        procedure.setInterface(interfase);
        MgProcedureTypeService.create(procedure);
        assertNotNull(procedure.getType());
    }

    @TestCase
    public void testInterfaceCompatibilitySimple(){
        MgInterface interfase = createInterface(
            "simpleInterface",
            createItnerfaceVariable("interfaceInput", MgInt32.getInstance()),
            createItnerfaceVariable("interfaceOutput", MgInt32.getInstance())
        );
        MgProcedure procedure = createProcedure(
            "simpleProcedure",
            createInstanceVariable("procedureInput", MgInt32.getInstance()),
            createInstanceVariable("procedureOutput", MgInt32.getInstance()),
            createInstanceVariable("procedureLocal", MgInt32.getInstance())
        );
        procedure.setInterface(interfase);
        MgProcedureTypeService.create(procedure);
        assertNotNull(procedure.getType());
    }

    @TestCase
    public void testIncompatibleInterface(){
        MgInterface interfase = createInterface(
            "simpleInterface",
            createItnerfaceVariable("interfaceInput", MgInt32.getInstance()),
            createItnerfaceVariable("interfaceOutput", MgInt32.getInstance())
        );
        MgProcedure procedure = createProcedure("simpleProcedure");
        procedure.setInterface(interfase);
        assertExceptionThrown(() -> {
            MgProcedureTypeService.create(procedure);
        });
    }

    @TestCase
    public void testIncompatibleInterfaceAnother(){
        MgInterface interfase = createInterface(
            "simpleInterface",
            createItnerfaceVariable("interfaceInput", MgInt32.getInstance()),
            createItnerfaceVariable("interfaceOutput", MgInt32.getInstance())
        );
        MgProcedure procedure = createProcedure(
            "simpleProcedure",
            createInstanceVariable("procedureInput", MgInt32.getInstance()),
            createInstanceVariable("procedureOutput", MgFloat32.getInstance()),
            createInstanceVariable("procedureLocal", MgInt32.getInstance())
        );
        procedure.setInterface(interfase);
        assertExceptionThrown(() -> {
            MgProcedureTypeService.create(procedure);
        });
    }

    @TestCase
    public void testCreateProcedureTypeHasVariablesInOrder(){
        MgInstanceVariable input = createInstanceVariable("input", MgInt32.getInstance());
        MgInstanceVariable output = createInstanceVariable("output", MgInt32.getInstance());
        MgInstanceVariable local = createInstanceVariable("local", MgInt32.getInstance());
        MgProcedure procedure = createProcedure("testProcedure", input, output, local);
        MgProcedureTypeService.create(procedure);
        assertNotNull(procedure.getType());
        assertNotNull(procedure.getType().getInstanceVariables());
        assertEquals(3, procedure.getType().getInstanceVariables().count());
        assertEquals(input, procedure.getType().getInstanceVariables().get(0));
        assertEquals(output, procedure.getType().getInstanceVariables().get(1));
        assertEquals(local, procedure.getType().getInstanceVariables().get(2));
    }
}
