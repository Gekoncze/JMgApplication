package cz.mg.test;

import cz.mg.application.entities.Named;

import java.util.Objects;


public class TestUtils {
    public static void print(String s){
        System.out.print(s);
    }

    public static void println(String s){
        System.out.println(s);
    }

    public static void println(){
        System.out.println();
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

    public static void printStackTrace(Throwable e){
        for(int i = 0; i < e.getStackTrace().length; i++){
            StackTraceElement element = e.getStackTrace()[i];
            println(
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
