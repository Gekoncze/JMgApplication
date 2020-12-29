package cz.mg.application.architecture;

import cz.mg.application.RuntimeEnvironment;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class MgSimpleRuntimeTest implements Test {
    public static void main(String[] args) {
        SingleTestRunner testRunner = new SingleTestRunner();
        testRunner.run(new MgSimpleRuntimeTest());
    }

    @TestCase
    public void testElementaryRuntimeArchitecture(){
        RuntimeEnvironment.run(() -> {});
    }
}
