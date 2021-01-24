package cz.mg.application.factories;

import cz.mg.application.entities.statical.parts.MgInterface;
import cz.mg.application.entities.statical.parts.variables.MgInterfaceVariable;
import cz.mg.collections.Clump;
import cz.mg.collections.array.Array;
import cz.mg.collections.text.Text;


public interface MgTestInterfaceFactory {
    public static MgInterface createInterface(String name){
        MgInterface interfase = new MgInterface();
        interfase.setName(new Text(name));
        return interfase;
    }

    public static MgInterface createInterface(
        String name,
        MgInterfaceVariable input,
        MgInterfaceVariable output
    ){
        return createInterface(
            name,
            new Array<>(input),
            new Array<>(output)
        );
    }

    public static MgInterface createInterface(
        String name,
        Clump<MgInterfaceVariable> input,
        Clump<MgInterfaceVariable> output
    ){
        MgInterface procedure = new MgInterface();
        procedure.setName(new Text(name));
        procedure.getInput().addCollectionLast(input);
        procedure.getOutput().addCollectionLast(output);
        return procedure;
    }
}
