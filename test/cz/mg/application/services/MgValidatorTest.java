package cz.mg.application.services;

import cz.mg.application.entities.runtime.types.MgClassType;
import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.validation.MgValidator;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.map.Map;
import cz.mg.collections.text.Text;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


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
        MgInstanceVariable variable = new MgInstanceVariable();
        variable.setName(new Text("TestVariable"));

        MgClass clazz = new MgClass();
        clazz.setName(new Text("TestClass"));
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
        MgClass animal = new MgClass();
        animal.setName(new Text("Animal"));

        MgClass cat = new MgClass();
        cat.setName(new Text("Cat"));
        cat.getBaseClasses().addLast(animal);

        MgClass plant = new MgClass();
        plant.setName(new Text("Plant"));

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
            createVariable(cat, "source"),
            createVariable(animal, "destination")
        );

        assertExceptionThrown(() -> {
            MgValidator.checkCompatibility(
                createVariable(animal, "source"),
                createVariable(cat, "destination")
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkCompatibility(
                createVariable(plant, "source"),
                createVariable(animal, "destination")
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkCompatibility(
                createVariable(cat, "source"),
                createVariable(plant, "destination")
            );
        });
    }

    @TestCase(order = 5)
    public void testCheckInputOutputCompatibility(){
        MgClass animal = new MgClass();
        animal.setName(new Text("Animal"));

        MgClass cat = new MgClass();
        cat.setName(new Text("Cat"));
        cat.getBaseClasses().addLast(animal);

        MgClass plant = new MgClass();
        plant.setName(new Text("Plant"));

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

        MgProcedure procedure = new MgProcedure();
        procedure.setName(new Text("babysit"));
        procedure.getInput().addLast(createVariable(animal, "animal1"));
        procedure.getInput().addLast(createVariable(animal, "animal2"));
        procedure.getOutput().addLast(createVariable(plant, "plant"));

        MgValidator.checkInputCompatibility(procedure,
            new Array<>(
                createVariable(cat, "srcCat1"),
                createVariable(cat, "srcCat2")
            )
        );

        MgValidator.checkOutputCompatibility(procedure,
            new Array<>(
                createVariable(plant, "dstPlant")
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
                    createVariable(cat, "srcCat1"),
                    createVariable(cat, "srcCat2"),
                    createVariable(cat, "srcCat3")
                )
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkOutputCompatibility(procedure,
                new Array<>(
                    createVariable(plant, "dstPlant1"),
                    createVariable(plant, "dstPlant2")
                )
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkInputCompatibility(procedure,
                new Array<>(
                    createVariable(cat, "srcCat"),
                    createVariable(plant, "srcPlant")
                )
            );
        });

        assertExceptionThrown(() -> {
            MgValidator.checkOutputCompatibility(procedure,
                new Array<>(
                    createVariable(cat, "dstCat")
                )
            );
        });
    }

    private MgInstanceVariable createVariable(MgClass clazz, String name){
        MgInstanceVariable variable = new MgInstanceVariable();
        variable.setName(new Text(name));
        variable.setDefinition(clazz);
        return variable;
    }
}
