package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;

import static cz.mg.application.factories.MgTestClassFactory.createClass;
import static cz.mg.application.factories.MgTestInterfaceFactory.createInterface;
import static cz.mg.application.factories.MgTestProcedureFactory.createProcedure;
import static cz.mg.application.factories.MgTestVariableFactory.createInstanceVariable;


@SuppressWarnings("unused")
public class MgClassTypeServiceTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgClassTypeServiceTest());
    }

    @TestCase(order = 1)
    public void testInheritanceMultiple(){
        MgClass pet = createClass("Pet");
        MgInstanceVariable collar = createInstanceVariable("collar");
        MgProcedure cuddle = createProcedure("cuddle");
        pet.getInstanceVariables().addLast(collar);
        pet.getProcedures().addLast(cuddle);

        MgClass being = createClass("Being");
        MgInstanceVariable health = createInstanceVariable("health");
        MgProcedure getHealth = createProcedure("getHealth");
        MgProcedure setHealth = createProcedure("setHealth");
        being.getInstanceVariables().addLast(health);
        being.getProcedures().addLast(getHealth);
        being.getProcedures().addLast(setHealth);

        MgClass animal = createClass("Animal");
        MgInstanceVariable ability = createInstanceVariable("ability");
        MgProcedure getAbility = createProcedure("getAbility");
        animal.getInstanceVariables().addLast(ability);
        animal.getProcedures().addLast(getAbility);
        animal.getBaseClasses().addLast(being);

        MgClass cat = createClass("Cat");
        MgInstanceVariable lives = createInstanceVariable("lives");
        MgProcedure meow = createProcedure("meow");
        cat.getInstanceVariables().addLast(lives);
        cat.getProcedures().addLast(meow);
        cat.getBaseClasses().addLast(animal);
        cat.getBaseClasses().addLast(pet);

        MgClassTypeService.create(cat);

        assertNotNull(pet.getType());
        assertNotNull(being.getType());
        assertNotNull(animal.getType());
        assertNotNull(cat.getType());

        assertContains(cat.getType().getInstanceVariables(), collar, 1);
        assertContains(cat.getType().getInstanceVariables(), health, 1);
        assertContains(cat.getType().getInstanceVariables(), ability, 1);
        assertContains(cat.getType().getInstanceVariables(), lives, 1);

        assertContains(cat.getType().getProcedures(), cuddle, 1);
        assertContains(cat.getType().getProcedures(), getHealth, 1);
        assertContains(cat.getType().getProcedures(), setHealth, 1);
        assertContains(cat.getType().getProcedures(), getAbility, 1);
        assertContains(cat.getType().getProcedures(), meow, 1);
    }

    @TestCase(order = 2)
    public void testInheritanceDiamond(){
        MgClass animal = createClass("Animal");
        MgInstanceVariable ability = createInstanceVariable("ability");
        MgProcedure getAbility = createProcedure("getAbility");
        animal.getInstanceVariables().addLast(ability);
        animal.getProcedures().addLast(getAbility);

        MgClass cat = createClass("Cat");
        MgInstanceVariable lives = createInstanceVariable("lives");
        MgProcedure meow = createProcedure("meow");
        cat.getInstanceVariables().addLast(lives);
        cat.getProcedures().addLast(meow);
        cat.getBaseClasses().addLast(animal);

        MgClass dog = createClass("Dog");
        MgInstanceVariable ball = createInstanceVariable("ball");
        MgProcedure bark = createProcedure("bark");
        dog.getInstanceVariables().addLast(ball);
        dog.getProcedures().addLast(bark);
        dog.getBaseClasses().addLast(animal);

        MgClass catDog = createClass("CatDog");
        MgInstanceVariable cake = createInstanceVariable("cake");
        MgProcedure bake = createProcedure("bake");
        catDog.getInstanceVariables().addLast(cake);
        catDog.getProcedures().addLast(bake);
        catDog.getBaseClasses().addLast(cat);
        catDog.getBaseClasses().addLast(dog);

        MgClassTypeService.create(catDog);

        assertNotNull(animal.getType());
        assertNotNull(cat.getType());
        assertNotNull(dog.getType());
        assertNotNull(catDog.getType());

        assertContains(catDog.getType().getInstanceVariables(), ability, 1);
        assertContains(catDog.getType().getInstanceVariables(), lives, 1);
        assertContains(catDog.getType().getInstanceVariables(), ball, 1);
        assertContains(catDog.getType().getInstanceVariables(), cake, 1);

        assertContains(catDog.getType().getProcedures(), getAbility, 1);
        assertContains(catDog.getType().getProcedures(), meow, 1);
        assertContains(catDog.getType().getProcedures(), bark, 1);
        assertContains(catDog.getType().getProcedures(), bake, 1);
    }

    @TestCase(order = 3)
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

    @TestCase(order = 4)
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

        assertContains(subClass.getType().getTypes(), baseClass.getType(), 1);

        assertContains(subClass.getType().getProcedures(), procedureOne, 1);
        assertContains(subClass.getType().getProcedures(), procedureTwo, 1);
        assertContains(subClass.getType().getProcedures(), procedureThree, 1);
        assertContains(subClass.getType().getProcedures(), procedureFour, 1);
        assertContains(subClass.getType().getProcedures(), procedureFive, 1);
        assertContains(subClass.getType().getProcedures(), procedureSix, 1);
    }

    @TestCase(order = 5)
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

    @TestCase(order = 6)
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

    @TestCase(order = 7)
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
