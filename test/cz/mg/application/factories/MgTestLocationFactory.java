package cz.mg.application.factories;

import cz.mg.application.entities.components.MgLocation;
import cz.mg.collections.text.Text;


public interface MgTestLocationFactory {
    public static MgLocation createLocation(String name){
        MgLocation location = new MgLocation();
        location.setName(new Text(name));
        return location;
    }
}
