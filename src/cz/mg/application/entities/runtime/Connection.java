package cz.mg.application.entities.runtime;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgStructuredObject;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgValidator;


public class Connection {
    @Mandatory @Link
    public final MgInstanceVariable source;

    @Mandatory @Link
    public final MgInstanceVariable destination;

    public Connection(MgInstanceVariable source, MgInstanceVariable destination) {
        MgValidator.checkCompatibility(source, destination);
        this.source = source;
        this.destination = destination;
    }

    public void run(MgStructuredObject source, MgStructuredObject destination){
        destination.setObject(this.destination, source.getObject(this.source));
    }
}
