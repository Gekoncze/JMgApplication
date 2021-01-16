package cz.mg.application.services;

import cz.mg.application.entities.runtime.types.MgClassType;
import cz.mg.application.entities.runtime.types.MgType;
import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.components.definitions.MgOperator;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.variables.MgVariable;
import cz.mg.application.services.validation.MgValidator;
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
        MgValidator validator = new MgValidator();
        assertExceptionThrown(() -> {
            validator.checkNotNull(null);
        });
    }

    @TestCase(order = 2)
    public void testCheckType(){
        MgValidator validator = new MgValidator();

        assertExceptionThrown(() -> {
            Object object = new MgInstanceVariable();
            validator.checkType(object, MgClass.class, (clazz) -> {});
        });

        Object object = new MgClass();
        validator.checkType(object, MgClass.class, (clazz) -> {});
    }
//
//    @TestCase(order = 3)
//    public void testCheckOwnership(){
//        MgValidator validator = new MgValidator();
//
//        MgVariable variable = new MgInstanceVariable();
//        variable.setName(new Text("TestVariable"));
//
//        MgClass clazz = new MgClass();
//        clazz.setName(new Text("TestClass"));
//        clazz.setType(
//            new MgClassType(
//                clazz,
//                new ReadonlyArray<>(),
//                new ReadonlyArray<>(),
//                new ReadonlyArray<>(),
//                new ReadonlyArray<>(),
//                new ReadonlyArray<>(),
//                new Map<>()
//            )
//        );
//
//        assertExceptionThrown(() -> {
//            validator.checkOwnership(clazz, variable);
//        });
//
//        clazz.setType(
//            new MgClassType(
//                clazz,
//                new ReadonlyArray<>(),
//                new ReadonlyArray<>(variable),
//                new ReadonlyArray<>(),
//                new ReadonlyArray<>(),
//                new ReadonlyArray<>(),
//                new Map<>()
//            )
//        );
//
//        validator.checkOwnership(clazz, variable);
//    }
//
//    @TestCase
//    public void testCheckCompatibility(){
//        MgValidator validator = new MgValidator();
//
//        MgClass animal = new MgClass();
//        animal.setName(new Text("Animal"));
//
//        MgClass cat = new MgClass();
//        cat.setName(new Text("Cat"));
//
//        MgClass plant = new MgClass();
//        plant.setName(new Text("Plant"));
//
//        animal.setType(new MgClassType(
//            animal,
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new Map<>()
//        ));
//
//        cat.setType(new MgClassType(
//            cat,
//            new ReadonlyArray<>(animal),
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new Map<>()
//        ));
//
//        plant.setType(new MgClassType(
//            plant,
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new ReadonlyArray<>(),
//            new Map<>()
//        ));
//
////        assertExceptionThrown(() -> {
////            validator.checkCompatibility();
////        });
//    }
//
//    @TestCase
//    public void test(){
//        MgValidator validator = new MgValidator();
//        assertExceptionThrown(() -> {
//
//        });
//    }
}
