package cz.mg.application.services;

import cz.mg.application.entities.statical.components.MgClass;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.MgProcedure;
import cz.mg.application.services.runtime.MgClassTypeService;
import cz.mg.collections.text.Text;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


@SuppressWarnings("unused")
public class MgClassTypeServiceTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgClassTypeServiceTest());
    }

    @TestCase(order = 1)
    public void testResolveSimpleInterface(){
        MgClass clazz = new MgClass(new Text("TestClass"));

        MgInterface mgInterface = new MgInterface(new Text("testInterface"));
        clazz.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = new MgProcedure(new Text("procedureOne"));
        MgProcedure procedureTwo = new MgProcedure(new Text("procedureTwo"));
        MgProcedure procedureThree = new MgProcedure(new Text("procedureThree"));

        procedureTwo.setInterface(mgInterface);

        clazz.getProcedures().addLast(procedureOne);
        clazz.getProcedures().addLast(procedureTwo);
        clazz.getProcedures().addLast(procedureThree);

        MgClassTypeService.create(clazz);
        assertNotNull(clazz.getType());
        assertEquals(procedureTwo, clazz.getType().getProcedure(mgInterface));
    }

    @TestCase(order = 2)
    public void testResolveInheritanceInterface(){
        MgClass baseClass = new MgClass(new Text("BaseClass"));

        MgInterface mgInterface = new MgInterface(new Text("testInterface"));
        baseClass.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = new MgProcedure(new Text("procedureOne"));
        MgProcedure procedureTwo = new MgProcedure(new Text("procedureTwo"));
        MgProcedure procedureThree = new MgProcedure(new Text("procedureThree"));

        procedureTwo.setInterface(mgInterface);

        baseClass.getProcedures().addLast(procedureOne);
        baseClass.getProcedures().addLast(procedureTwo);
        baseClass.getProcedures().addLast(procedureThree);

        MgClass subClass = new MgClass(new Text("SuperClass"));
        subClass.getBaseClasses().addLast(baseClass);

        MgProcedure procedureFour = new MgProcedure(new Text("procedureFour"));
        MgProcedure procedureFive = new MgProcedure(new Text("procedureFive"));
        MgProcedure procedureSix = new MgProcedure(new Text("procedureSix"));

        procedureSix.setInterface(mgInterface);

        subClass.getProcedures().addLast(procedureFour);
        subClass.getProcedures().addLast(procedureFive);
        subClass.getProcedures().addLast(procedureSix);

        MgClassTypeService.create(subClass);
        assertNotNull(baseClass.getType());
        assertNotNull(subClass.getType());
        assertEquals(procedureTwo, baseClass.getType().getProcedure(mgInterface));
        assertEquals(procedureSix, subClass.getType().getProcedure(mgInterface));

        assertContains(
            subClass.getType().getTypes(),
            baseClass.getType()
        );

        assertContains(
            subClass.getType().getProcedures(),
            procedureOne,
            procedureTwo,
            procedureThree,
            procedureFour,
            procedureFive,
            procedureSix
        );
    }

    @TestCase(order = 3)
    public void testResolveComplexInheritanceInterface(){
        MgClass animalClass = new MgClass(new Text("AnimalClass"));

        MgInterface mgInterface = new MgInterface(new Text("animalInterface"));
        animalClass.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = new MgProcedure(new Text("procedureOne"));
        MgProcedure procedureTwo = new MgProcedure(new Text("procedureTwo"));
        MgProcedure procedureThree = new MgProcedure(new Text("procedureThree"));

        procedureTwo.setInterface(mgInterface);

        animalClass.getProcedures().addLast(procedureOne);
        animalClass.getProcedures().addLast(procedureTwo);
        animalClass.getProcedures().addLast(procedureThree);

        MgClass catClass = new MgClass(new Text("CatClass"));
        catClass.getBaseClasses().addLast(animalClass);

        MgProcedure procedureFour = new MgProcedure(new Text("procedureFour"));
        MgProcedure procedureFive = new MgProcedure(new Text("procedureFive"));
        MgProcedure procedureSix = new MgProcedure(new Text("procedureSix"));

        procedureFour.setInterface(mgInterface);

        catClass.getProcedures().addLast(procedureFour);
        catClass.getProcedures().addLast(procedureFive);
        catClass.getProcedures().addLast(procedureSix);

        MgClass dogClass = new MgClass(new Text("DogClass"));
        dogClass.getBaseClasses().addLast(animalClass);

        MgProcedure procedureSeven = new MgProcedure(new Text("procedureSeven"));
        MgProcedure procedureEight = new MgProcedure(new Text("procedureEight"));
        MgProcedure procedureNine = new MgProcedure(new Text("procedureNine"));

        procedureNine.setInterface(mgInterface);

        dogClass.getProcedures().addLast(procedureSeven);
        dogClass.getProcedures().addLast(procedureEight);
        dogClass.getProcedures().addLast(procedureNine);

        MgClass catDogClass = new MgClass(new Text("CatDogClass"));
        catDogClass.getBaseClasses().addLast(catClass);
        catDogClass.getBaseClasses().addLast(dogClass);

        MgProcedure procedureTen = new MgProcedure(new Text("procedureFour"));
        MgProcedure procedureEleven = new MgProcedure(new Text("procedureFive"));
        MgProcedure procedureTwelve = new MgProcedure(new Text("procedureSix"));

        procedureTwelve.setInterface(mgInterface);

        catDogClass.getProcedures().addLast(procedureTen);
        catDogClass.getProcedures().addLast(procedureEleven);
        catDogClass.getProcedures().addLast(procedureTwelve);

        MgClassTypeService.create(catDogClass);
        assertNotNull(animalClass.getType());
        assertNotNull(catClass.getType());
        assertNotNull(dogClass.getType());
        assertNotNull(catDogClass.getType());
        assertEquals(procedureTwo, animalClass.getType().getProcedure(mgInterface));
        assertEquals(procedureFour, catClass.getType().getProcedure(mgInterface));
        assertEquals(procedureNine, dogClass.getType().getProcedure(mgInterface));
        assertEquals(procedureTwelve, catDogClass.getType().getProcedure(mgInterface));
    }

    @TestCase(order = 4)
    public void testResolveMissingInterfaceProcedureError(){
        MgClass clazz = new MgClass(new Text("TestClass"));

        MgInterface mgInterface = new MgInterface(new Text("interface"));
        clazz.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = new MgProcedure(new Text("procedureOne"));
        MgProcedure procedureTwo = new MgProcedure(new Text("procedureTwo"));
        MgProcedure procedureThree = new MgProcedure(new Text("procedureThree"));

        clazz.getProcedures().addLast(procedureOne);
        clazz.getProcedures().addLast(procedureTwo);
        clazz.getProcedures().addLast(procedureThree);

        assertExceptionThrown(() -> MgClassTypeService.create(clazz));
        assertNull(clazz.getType());
    }

    @TestCase(order = 5)
    public void testResolveMissingInterfaceProcedureAllowedForAbstract(){
        MgClass clazz = new MgClass(new Text("TestClass"));
        clazz.getOptions().setAbstract(true);

        MgInterface mgInterface = new MgInterface(new Text("interface"));
        clazz.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = new MgProcedure(new Text("procedureOne"));
        MgProcedure procedureTwo = new MgProcedure(new Text("procedureTwo"));
        MgProcedure procedureThree = new MgProcedure(new Text("procedureThree"));

        clazz.getProcedures().addLast(procedureOne);
        clazz.getProcedures().addLast(procedureTwo);
        clazz.getProcedures().addLast(procedureThree);

        MgClassTypeService.create(clazz);
        assertNotNull(clazz.getType());
    }
}
