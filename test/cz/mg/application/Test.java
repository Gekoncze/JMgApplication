package cz.mg.application;

import cz.mg.application.entities.Named;


public class Test {
    public static void begin(){
        System.out.print("Running " + Thread.currentThread().getStackTrace()[2].getMethodName() + " ... ");
    }

    public static void end(){
        System.out.println("OK");
    }

    public static void error(String message){
        System.out.println("ERROR");
        throw new TestFailedException(message);
    }

    public static void assertNotNull(Object object){
        if(object == null){
            error("Unexpected null value.");
        }
    }

    public static void assertEquals(Named expectation, Named reality){
        if(expectation != reality){
            String expectationName = expectation == null ? "null" : "'" + expectation.getName() + "'";
            String realityName = reality == null ? "null" : "'" + reality.getName() + "'";
            error("Expected " + expectationName + ", but got " + realityName + ".");
        }
    }

    public static void expectedError(Runnable runnable){
        try {
            runnable.run();
            error("Expected an exception, but got none.");
        } catch (RuntimeException e){
        }
    }
}
