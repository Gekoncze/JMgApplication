package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.collections.list.List;
import cz.mg.collections.text.Text;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class MgClassServiceTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgClassServiceTest());
    }

    @TestCase(order = 1)
    public void testForEachMultiple(){
        MgClass pet = new MgClass();
        pet.setName(new Text("Pet"));

        MgClass being = new MgClass();
        being.setName(new Text("Being"));

        MgClass animal = new MgClass();
        animal.setName(new Text("Animal"));
        animal.getBaseClasses().addLast(being);

        MgClass cat = new MgClass();
        cat.setName(new Text("Cat"));
        cat.getBaseClasses().addLast(animal);
        cat.getBaseClasses().addLast(pet);

        List<MgClass> visitedClasses = new List<>();
        MgClassService.forEachClass(cat, (clazz -> {
            visitedClasses.addLast(clazz);
        }));

        assertContains(visitedClasses, pet, 1);
        assertContains(visitedClasses, being, 1);
        assertContains(visitedClasses, animal, 1);
        assertContains(visitedClasses, cat, 1);
    }

    @TestCase(order = 2)
    public void testForEachCircular(){
        MgClass being = new MgClass();
        being.setName(new Text("Being"));

        MgClass animal = new MgClass();
        animal.setName(new Text("Animal"));
        animal.getBaseClasses().addLast(being);

        MgClass cat = new MgClass();
        cat.setName(new Text("Cat"));
        cat.getBaseClasses().addLast(animal);

        being.getBaseClasses().addLast(cat);

        assertExceptionThrown(() -> {
            List<MgClass> visitedClasses = new List<>();
            MgClassService.forEachClass(cat, (clazz -> {
                visitedClasses.addLast(clazz);
            }));
        });
    }

    @TestCase(order = 3)
    public void testForEachDiamond(){
        MgClass being = new MgClass();
        being.setName(new Text("Being"));

        MgClass animal = new MgClass();
        animal.setName(new Text("Animal"));
        animal.getBaseClasses().addLast(being);

        MgClass cat = new MgClass();
        cat.setName(new Text("Cat"));
        cat.getBaseClasses().addLast(animal);

        MgClass dog = new MgClass();
        dog.setName(new Text("Dog"));
        dog.getBaseClasses().addLast(animal);

        MgClass catDog = new MgClass();
        catDog.setName(new Text("CatDog"));
        catDog.getBaseClasses().addLast(cat);
        catDog.getBaseClasses().addLast(dog);

        List<MgClass> visitedClasses = new List<>();
        MgClassService.forEachClass(catDog, (clazz -> {
            visitedClasses.addLast(clazz);
        }));

        assertContains(visitedClasses, being, 1);
        assertContains(visitedClasses, animal, 1);
        assertContains(visitedClasses, cat, 1);
        assertContains(visitedClasses, dog, 1);
        assertContains(visitedClasses, catDog, 1);
    }
}
