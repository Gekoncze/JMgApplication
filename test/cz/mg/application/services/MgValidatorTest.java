package cz.mg.application.services;

import cz.mg.application.entities.runtime.types.MgClassType;
import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.validation.MgValidator;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.map.Map;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;

import static cz.mg.application.factories.MgTestClassFactory.createClass;
import static cz.mg.application.factories.MgTestProcedureFactory.createProcedure;
import static cz.mg.application.factories.MgTestVariableFactory.createInstanceVariable;


public class MgValidatorTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgValidatorTest());
    }

    @TestCase(order = 1)
    public void testCheckNotNull(){
        assertExceptionThrown(() -> {
            MgValidator.checkNotNull(null);
        });
    }

    @TestCase(order = 2)
    public void testCheckType(){
        assertExceptionThrown(() -> {
            Object object = new MgInstanceVariable();
            MgValidator.checkType(object, MgClass.class, (clazz) -> {});
        });

        Object object = new MgClass();
        MgValidator.checkType(object, MgClass.class, (clazz) -> {});
    }

    @TestCase(order = 3)
    public void testCheckOwnership(){
        MgInstanceVariable variable = createInstanceVariable("TestVariable");
        MgClass clazz = createClass("TestClass");

        clazz.setType(
            new MgClassType(
                clazz,
                new ReadonlyArray<>(),
                new ReadonlyArray<>(),
                new ReadonlyArray<>(),
                new ReadonlyArray<>(),
                new ReadonlyArray<>(),
                new Map<>()
            )
        );

        assertExceptionThrown(() -> {
            MgValidator.checkOwnership(clazz, variable);
        });

        clazz.setType(
            new MgClassType(
                clazz,
                new ReadonlyArray<>(),
                new ReadonlyArray<>(variable),
                new ReadonlyArray<>(),
                new ReadonlyArray<>(),
                new ReadonlyArray<>(),
                new Map<>()
            )
        );

        MgValidator.checkOwnership(clazz, variable);
    }

    @TestCase(order = 4)
    public void testCheckCompatibility(){
        MgClass animal = createClass("Animal");
        MgClass cat = createClass("Cat", animal);
        MgClass plant = createClass("Plant");

        animal.setType(new MgClassType(
            animal,
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new Map<>()
        ));

        cat.setType(new MgClassType(
            cat,
            new ReadonlyArray<>(animal.getType()),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new Map<>()
        ));

        plant.setType(new MgClassType(
            plant,
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new Map<>()
        ));

        MgValidator.checkCompatibility(
            createInstanceVariable("source", cat),
            createInstanceVariable("destination", animal)
        );

        assertExceptionThrown(() -> {
            MgValidator.checkCompatibility(
                createInstanceVariable("source", animal),
                createInstanceVariable("destination", cat)
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkCompatibility(
                createInstanceVariable("source", plant),
                createInstanceVariable("destination", animal)
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkCompatibility(
                createInstanceVariable("source", cat),
                createInstanceVariable("destination", plant)
            );
        });
    }

    @TestCase(order = 5)
    public void testCheckInputOutputCompatibility(){
        MgClass animal = createClass("Animal");
        MgClass cat = createClass("Cat", animal);
        MgClass plant = createClass("Plant");

        animal.setType(new MgClassType(
            animal,
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new Map<>()
        ));

        cat.setType(new MgClassType(
            cat,
            new ReadonlyArray<>(animal.getType()),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new Map<>()
        ));

        plant.setType(new MgClassType(
            plant,
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new Map<>()
        ));

        MgProcedure procedure = createProcedure("babysit");
        procedure.getInput().addLast(createInstanceVariable("animal1", animal));
        procedure.getInput().addLast(createInstanceVariable("animal2", animal));
        procedure.getOutput().addLast(createInstanceVariable("plant", plant));

        MgValidator.checkInputCompatibility(procedure,
            new Array<>(
                createInstanceVariable("srcCat1", cat),
                createInstanceVariable("srcCat2", cat)
            )
        );

        MgValidator.checkOutputCompatibility(procedure,
            new Array<>(
                createInstanceVariable("dstPlant", plant)
            )
        );

        assertExceptionThrown(() -> {
            MgValidator.checkInputCompatibility(procedure,
                new Array<>()
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkOutputCompatibility(procedure,
                new Array<>()
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkInputCompatibility(procedure,
                new Array<>(
                    createInstanceVariable("srcCat1", cat),
                    createInstanceVariable("srcCat2", cat),
                    createInstanceVariable("srcCat3", cat)
                )
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkOutputCompatibility(procedure,
                new Array<>(
                    createInstanceVariable("dstPlant1", plant),
                    createInstanceVariable("dstPlant2", plant)
                )
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkInputCompatibility(procedure,
                new Array<>(
                    createInstanceVariable("srcCat", cat),
                    createInstanceVariable("srcPlant", plant)
                )
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkOutputCompatibility(procedure,
                new Array<>(
                    createInstanceVariable("dstCat", cat)
                )
            );
        });
    }
}
