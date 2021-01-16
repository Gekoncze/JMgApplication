package cz.mg.application.services;

import cz.mg.application.entities.statical.components.definitions.MgClass;
import cz.mg.collections.list.List;


public class MgClassService {
    public static void forEachClass(MgClass clazz, ClassVisitor classVisitor){
        forEachClass(clazz, classVisitor, new List<>(), new List<>());
    }

    private static void forEachClass(MgClass clazz, ClassVisitor classVisitor, List<MgClass> path, List<MgClass> visited){
        if(path.contains(clazz)) throw new RuntimeException("Circular inheritance detected.");
        path.addLast(clazz);

        if(visited.contains(clazz)) return;
        visited.addLast(clazz);

        classVisitor.visit(clazz);

        for(MgClass base : clazz.getBaseClasses()){
            forEachClass(base, classVisitor, new List<>(path), visited);
        }
    }

    public interface ClassVisitor {
        void visit(MgClass clazz);
    }
}
