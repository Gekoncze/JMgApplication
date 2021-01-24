package cz.mg.application.factories;

import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.entities.parts.commands.MgReturnCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.collections.Clump;
import cz.mg.collections.array.Array;
import cz.mg.collections.text.Text;


public interface MgTestProcedureFactory {
    public static MgProcedure createProcedure(String name){
        return createProcedure(
            name,
            new Array<>(),
            new Array<>(),
            new Array<>(),
            new Array<>(new MgReturnCommand())
        );
    }

    public static MgProcedure createProcedure(
        String name,
        MgInstanceVariable input,
        MgInstanceVariable output,
        MgInstanceVariable local
    ){
        return createProcedure(
            name,
            new Array<>(input),
            new Array<>(output),
            new Array<>(local),
            new Array<>(new MgReturnCommand())
        );
    }

    public static MgProcedure createProcedure(
        String name,
        MgInstanceVariable input,
        MgInstanceVariable output,
        MgInstanceVariable local,
        Clump<MgStandaloneCommand> commands
    ){
        return createProcedure(
            name,
            new Array<>(input),
            new Array<>(output),
            new Array<>(local),
            commands
        );
    }

    public static MgProcedure createProcedure(
        String name,
        Clump<MgInstanceVariable> input,
        Clump<MgInstanceVariable> output,
        Clump<MgInstanceVariable> local,
        Clump<MgStandaloneCommand> commands
    ){
        MgProcedure procedure = new MgProcedure();
        procedure.setName(new Text(name));
        procedure.getInput().addCollectionLast(input);
        procedure.getOutput().addCollectionLast(output);
        procedure.getLocal().addCollectionLast(local);
        procedure.getCommands().addCollectionLast(commands);
        return procedure;
    }
}
