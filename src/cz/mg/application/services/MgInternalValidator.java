package cz.mg.application.services;

import cz.mg.application.services.exceptions.InternalException;
import cz.mg.collections.array.Array;
import cz.mg.collections.text.Text;


public class MgInternalValidator extends MgService {
    public static void checkInterfaceMandatory(Object object, Class... expectations){
        int count = checkInterfaceCount(object, expectations);

        if(count < 1){
            throw new InternalException("Class " + object.getClass().getSimpleName() + " must implement one of the following interfaces: " + new Array<>(expectations).toText(", ", expectation -> new Text(expectation.getSimpleName())) + ".");
        }

        if(count > 1){
            throw new InternalException("Class " + object.getClass().getSimpleName() + " cannot implement more than one of the following interfaces: " + new Array<>(expectations).toText(", ", expectation -> new Text(expectation.getSimpleName())) + ".");
        }
    }

    public static void checkInterfaceOptional(Object object, Class... expectations){
        int count = checkInterfaceCount(object, expectations);

        if(count > 1){
            throw new InternalException("Class " + object.getClass().getSimpleName() + " cannot implement more than one of the following interfaces: " + new Array<>(expectations).toText(", ", expectation -> new Text(expectation.getSimpleName())) + ".");
        }
    }

    private static int checkInterfaceCount(Object object, Class... expectations){
        int count = 0;
        for(Class expectation : expectations){
            if(expectation.isAssignableFrom(object.getClass())){
                count++;
            }
        }
        return count;
    }
}
