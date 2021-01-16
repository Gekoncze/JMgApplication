package cz.mg.application.entities.runtime.types;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.application.entities.runtime.objects.MgObject;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.text.Text;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class MgTypeTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgTypeTest());
    }

    private final TestType animal = new TestType();
    private final TestType cat = new TestType(animal);
    private final TestType plant = new TestType();

    public MgTypeTest() {
    }

    @TestCase(order = 1)
    public void testIs(){
        assertEquals(cat.is(animal), true);
        assertEquals(animal.is(cat), false);

        assertEquals(plant.is(animal), false);
        assertEquals(animal.is(plant), false);
    }

    @TestCase(order = 2)
    public void testMaybe(){
        assertEquals(animal.maybe(cat), true);
        assertEquals(cat.maybe(animal), true);

        assertEquals(plant.maybe(animal), false);
        assertEquals(animal.maybe(plant), false);
    }

    private static class TestDefinition extends MgDefinition {
        @Optional @Cache
        private MgType type;

        public TestDefinition(String name) {
            setName(new Text(name));
        }

        @Override
        public MgType getType() {
            return type;
        }

        public void setType(MgType type) {
            this.type = type;
        }
    }

    private static class TestType extends MgType {
        public TestType(MgType... types) {
            super(new ReadonlyArray<>(types));
        }

        @Override
        public MgObject create() {
            throw new UnsupportedOperationException();
        }
    }
}
