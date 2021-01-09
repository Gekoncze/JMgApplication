package cz.mg.application.entities.runtime.types;

import cz.mg.application.entities.runtime.objects.MgObject;
import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.map.Map;
import cz.mg.collections.text.Text;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class MgClassTypeTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgClassTypeTest());
    }

    @TestCase
    public void testCreate(){
        MgClass clazz = new MgClass();
        clazz.setName(new Text("FooBar"));

        MgClassType classType = new MgClassType(
            clazz,
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new ReadonlyArray<>(),
            new Map<>()
        );

        MgObject object = classType.create();
        assertEquals(classType, object.getType());
    }
}
