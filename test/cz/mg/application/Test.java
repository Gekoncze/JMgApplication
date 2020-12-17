package cz.mg.application;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;

import static cz.mg.application.TestUtils.*;


public class Test {
    private static RuntimeException EXCEPTION = null;

    public static void beginTests(){
        System.out.println("Running tests in " + getClassName());
    }

    public static void endTests(){
        System.out.println();
        try { Thread.sleep(25); } catch (InterruptedException e){ throw new RuntimeException(e); }
        if(EXCEPTION != null) printStackTrace(EXCEPTION);
    }

    public static void runTest(Runnable runnable){
        try {
            runnable.run();
        } catch (RuntimeException e){
            if(EXCEPTION == null) EXCEPTION = e;
            System.out.println("ERROR");
        }
    }

    public static void begin(){
        System.out.print("    Running " + getMethodName() + " ... ");
    }

    public static void end(){
        System.out.println("OK");
    }

    public static void error(String message){
        System.out.println("ERROR");
        throw new TestFailedException(message);
    }

    public static void assertNull(Object object){
        if(object != null){
            error("Unexpected non-null value.");
        }
    }

    public static void assertNotNull(Object object){
        if(object == null){
            error("Unexpected null value.");
        }
    }

    public static void assertEquals(Object expectation, Object reality){
        if(expectation != reality){
            error("Expected " + getName(expectation) + ", but got " + getName(reality) + ".");
        }
    }

    public static void assertContains(Clump colelction, Object... items){
        List<Object> missing = new List<>();
        for(Object item : items){
            if(!Clump.contains(colelction, item)){
                missing.addLast(item);
            }
        }
        if(!missing.isEmpty()){
            List<String> names = new List<>();
            for(Object miss : missing){
                names.addLast(
                    getName(miss)
                );
            }
            throw new TestFailedException("Missing " + names.toText(", ") + " in collection.");
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
