package cz.mg.test;

import java.lang.reflect.Method;


public class TestException extends RuntimeException {
    private final Method testCase;

    public TestException(Method testCase, Throwable cause) {
        super(notNull(cause));
        this.testCase = testCase;
    }

    public Method getTestCase() {
        return testCase;
    }

    private static Throwable notNull(Throwable cause){
        if(cause == null) throw new NullPointerException();
        return cause;
    }
}
