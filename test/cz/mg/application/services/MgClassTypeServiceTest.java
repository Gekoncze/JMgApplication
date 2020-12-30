package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
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

    private static MgClass createClass(String name){
        MgClass clazz = new MgClass();
        clazz.setName(new Text(name));
        return clazz;
    }

    private static MgProcedure createProcedure(String name){
        MgProcedure procedure = new MgProcedure();
        procedure.setName(new Text(name));
        return procedure;
    }

    private static MgInterface createInterface(String name){
        MgInterface iinterface = new MgInterface();
        iinterface.setName(new Text(name));
        return iinterface;
    }

    @TestCase(order = 1)
    public void testResolveSimpleInterface(){
        MgClass clazz = createClass("TestClass");

        MgInterface mgInterface = createInterface("testInterface");
        clazz.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = createProcedure("procedureOne");
        MgProcedure procedureTwo = createProcedure("procedureTwo");
        MgProcedure procedureThree = createProcedure("procedureThree");

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
        MgClass baseClass = createClass("BaseClass");

        MgInterface mgInterface = createInterface("testInterface");
        baseClass.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = createProcedure("procedureOne");
        MgProcedure procedureTwo = createProcedure("procedureTwo");
        MgProcedure procedureThree = createProcedure("procedureThree");

        procedureTwo.setInterface(mgInterface);

        baseClass.getProcedures().addLast(procedureOne);
        baseClass.getProcedures().addLast(procedureTwo);
        baseClass.getProcedures().addLast(procedureThree);

        MgClass subClass = createClass("SuperClass");
        subClass.getBaseClasses().addLast(baseClass);

        MgProcedure procedureFour = createProcedure("procedureFour");
        MgProcedure procedureFive = createProcedure("procedureFive");
        MgProcedure procedureSix = createProcedure("procedureSix");

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
        MgClass animalClass = createClass("AnimalClass");

        MgInterface mgInterface = createInterface("animalInterface");
        animalClass.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = createProcedure("procedureOne");
        MgProcedure procedureTwo = createProcedure("procedureTwo");
        MgProcedure procedureThree = createProcedure("procedureThree");

        procedureTwo.setInterface(mgInterface);

        animalClass.getProcedures().addLast(procedureOne);
        animalClass.getProcedures().addLast(procedureTwo);
        animalClass.getProcedures().addLast(procedureThree);

        MgClass catClass = createClass("CatClass");
        catClass.getBaseClasses().addLast(animalClass);

        MgProcedure procedureFour = createProcedure("procedureFour");
        MgProcedure procedureFive = createProcedure("procedureFive");
        MgProcedure procedureSix = createProcedure("procedureSix");

        procedureFour.setInterface(mgInterface);

        catClass.getProcedures().addLast(procedureFour);
        catClass.getProcedures().addLast(procedureFive);
        catClass.getProcedures().addLast(procedureSix);

        MgClass dogClass = createClass("DogClass");
        dogClass.getBaseClasses().addLast(animalClass);

        MgProcedure procedureSeven = createProcedure("procedureSeven");
        MgProcedure procedureEight = createProcedure("procedureEight");
        MgProcedure procedureNine = createProcedure("procedureNine");

        procedureNine.setInterface(mgInterface);

        dogClass.getProcedures().addLast(procedureSeven);
        dogClass.getProcedures().addLast(procedureEight);
        dogClass.getProcedures().addLast(procedureNine);

        MgClass catDogClass = createClass("CatDogClass");
        catDogClass.getBaseClasses().addLast(catClass);
        catDogClass.getBaseClasses().addLast(dogClass);

        MgProcedure procedureTen = createProcedure("procedureFour");
        MgProcedure procedureEleven = createProcedure("procedureFive");
        MgProcedure procedureTwelve = createProcedure("procedureSix");

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
        MgClass clazz = createClass("TestClass");

        MgInterface mgInterface = createInterface("interface");
        clazz.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = createProcedure("procedureOne");
        MgProcedure procedureTwo = createProcedure("procedureTwo");
        MgProcedure procedureThree = createProcedure("procedureThree");

        clazz.getProcedures().addLast(procedureOne);
        clazz.getProcedures().addLast(procedureTwo);
        clazz.getProcedures().addLast(procedureThree);

        assertExceptionThrown(() -> MgClassTypeService.create(clazz));
        assertNull(clazz.getType());
    }

    @TestCase(order = 5)
    public void testResolveMissingInterfaceProcedureAllowedForAbstract(){
        MgClass clazz = createClass("TestClass");
        clazz.getOptions().setAbstract(true);

        MgInterface mgInterface = createInterface("interface");
        clazz.getInterfaces().addLast(mgInterface);

        MgProcedure procedureOne = createProcedure("procedureOne");
        MgProcedure procedureTwo = createProcedure("procedureTwo");
        MgProcedure procedureThree = createProcedure("procedureThree");

        clazz.getProcedures().addLast(procedureOne);
        clazz.getProcedures().addLast(procedureTwo);
        clazz.getProcedures().addLast(procedureThree);

        MgClassTypeService.create(clazz);
        assertNotNull(clazz.getType());
    }
}
