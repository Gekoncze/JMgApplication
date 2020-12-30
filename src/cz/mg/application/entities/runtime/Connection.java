package cz.mg.application.entities.runtime;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.runtime.objects.MgStructuredObject;
import cz.mg.application.entities.statical.parts.MgVariable;


public class Connection {
    @Mandatory @Link
    public final MgVariable source;

    @Mandatory @Link
    public final MgVariable destination;

    public Connection(MgVariable source, MgVariable destination) {
        this.source = source;
        this.destination = destination;
    }

    public void run(MgStructuredObject source, MgStructuredObject destination){
        destination.setObject(this.destination, source.getObject(this.source));
    }
}
