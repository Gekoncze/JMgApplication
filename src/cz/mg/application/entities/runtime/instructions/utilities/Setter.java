package cz.mg.application.entities.runtime.instructions.utilities;

import cz.mg.application.entities.runtime.objects.MgStructuredObject;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;


public class Setter {
    public void set(MgStructuredObject source, MgInstanceVariable sourceVariable, MgStructuredObject destination, MgInstanceVariable destinationVariable){
        destination.setObject(destinationVariable, source.getObject(sourceVariable));
    }
}
