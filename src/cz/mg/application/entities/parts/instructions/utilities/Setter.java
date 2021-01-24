package cz.mg.application.entities.parts.instructions.utilities;

import cz.mg.application.entities.objects.MgStructuredObject;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;


public class Setter {
    public void set(MgStructuredObject source, MgInstanceVariable sourceVariable, MgStructuredObject destination, MgInstanceVariable destinationVariable){
        destination.setObject(destinationVariable, source.getObject(sourceVariable));
    }
}
