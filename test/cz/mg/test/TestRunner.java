package cz.mg.test;

import cz.mg.collections.list.List;
import java.lang.reflect.Method;
import static cz.mg.test.TestUtils.*;


public class TestRunner {
    public static void run(Test test){
        List<TestException> failedTestCases = new List<>();

        println("Running tests in " + test.getClass().getSimpleName());
        for(Method testCase : getTestCases(test.getClass())){
            TestException result = run(test, testCase);
            if(result != null) failedTestCases.addLast(result);
        }

        println();
        for(TestException failedTestCase : failedTestCases){
            println("Exception in " + failedTestCase.getTestCase().getName() + ":");
            String type = failedTestCase.getCause().getClass().getSimpleName();
            String message = failedTestCase.getCause().getMessage();
            println("    " + type + " " + '"' + message + '"');
            printStackTrace(failedTestCase.getCause());
            println();
        }
    }

    private static TestException run(Test test, Method testCase){
        print("    Running " + testCase.getName() + " ... ");
        try {
            testCase.invoke(test);
            println("OK");
        } catch (Throwable e){
            e = unwrap(e);
            if(e instanceof DiscrepancyException){
                println("FAILED");
            } else {
                println("ERROR");
            }
            return new TestException(testCase, e);
        }
        return null;
    }

    private static List<Method> getTestCases(Class<? extends Test> clazz){
        List<Method> testCases = new List<>();
        for(Method method : clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(TestCase.class)){
                if(!method.isAnnotationPresent(Disabled.class)){
                    TestCase annotation = method.getAnnotation(TestCase.class);
                    testCases.add(method, annotation.order());
                }
            }
        }
        return testCases;
    }

    private static Throwable unwrap(Throwable e){
        while(e.getCause() != null){
            e = e.getCause();
        }
        return e;
    }
}
