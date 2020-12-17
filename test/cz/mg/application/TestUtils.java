package cz.mg.application;

import cz.mg.application.entities.Named;

import java.util.Objects;


public class TestUtils {
    public static String getMethodName(){
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    public static String getClassName(){
        return getSimpleName(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static String getName(Object object){
        if(object == null){
            return "null";
        } else if(object instanceof Named){
            return "'" + ((Named) object).getName() + "'";
        } else {
            return getSimpleName(Objects.toString(object));
        }
    }

    private static String getSimpleName(String className){
        int index = className.lastIndexOf(".") + 1;
        if(index < 0) index = 0;
        if(index > className.length()) index = className.length();
        return className.substring(index);
    }

    public static void printStackTrace(RuntimeException e){
        System.out.println(e.getMessage());
        for(int i = 1; i < e.getStackTrace().length; i++){
            StackTraceElement element = e.getStackTrace()[i];
            System.out.println(
                "    ." +
                "(" +
                getSimpleName(element.getClassName()) +
                ".java:" +
                element.getLineNumber() +
                ")"
            );
        }
    }
}
