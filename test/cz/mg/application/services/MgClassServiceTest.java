package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;

import static cz.mg.application.factories.MgTestClassFactory.createClass;


public class MgClassServiceTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgClassServiceTest());
    }

    @TestCase(order = 1)
    public void testForEachMultiple(){
        MgClass pet = createClass("Pet");
        MgClass being = createClass("Being");
        MgClass animal = createClass("Animal", being);
        MgClass cat = createClass("Cat", animal, pet);

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
        MgClass being = createClass("Being");
        MgClass animal = createClass("Animal", being);
        MgClass cat = createClass("Cat", animal);

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
        MgClass being = createClass("Being");
        MgClass animal = createClass("Animal", being);
        MgClass cat = createClass("Cat", animal);
        MgClass dog = createClass("Dog", animal);
        MgClass catDog = createClass("CatDog", cat, dog);

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
