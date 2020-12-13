package cz.mg.application.services.statical;

import cz.mg.application.entities.statical.components.MgClass;
import cz.mg.collections.list.List;


public class MgClassService {
    public static void forEachClass(MgClass clazz, ClassVisitor classVisitor){
        forEachClass(clazz, classVisitor, new List<>());
    }

    private static void forEachClass(MgClass clazz, ClassVisitor classVisitor, List<MgClass> path){
        if(path.contains(clazz)) throw new RuntimeException("Circular inheritance detected.");
        path.addLast(clazz);
        for(MgClass base : clazz.getBaseClasses()){
            forEachClass(base, classVisitor, new List<>(path));
        }
        classVisitor.visit(clazz);
    }

    public interface ClassVisitor {
        void visit(MgClass clazz);
    }
}
