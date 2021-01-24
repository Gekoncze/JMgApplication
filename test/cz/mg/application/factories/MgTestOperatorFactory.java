package cz.mg.application.factories;

import cz.mg.application.entities.components.definitions.MgBinaryOperator;
import cz.mg.application.entities.components.definitions.MgLunaryOperator;
import cz.mg.application.entities.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.parts.commands.MgReturnCommand;
import cz.mg.application.entities.parts.commands.interfaces.MgStandaloneCommand;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.collections.Clump;
import cz.mg.collections.array.Array;
import cz.mg.collections.text.Text;


public interface MgTestOperatorFactory {
    public static MgBinaryOperator createBinaryOperator(String name){
        MgBinaryOperator operator = new MgBinaryOperator();
        operator.setName(new Text(name));
        return operator;
    }

    public static MgBinaryOperator createBinaryOperator(
        String name,
        MgInstanceVariable leftInput,
        MgInstanceVariable rightInput,
        MgInstanceVariable output
    ){
        return createBinaryOperator(
            name,
            leftInput,
            rightInput,
            output,
            new Array<>(new MgReturnCommand())
        );
    }

    public static MgBinaryOperator createBinaryOperator(
        String name,
        MgInstanceVariable leftInput,
        MgInstanceVariable rightInput,
        MgInstanceVariable output,
        Clump<MgStandaloneCommand> commands
    ){
        MgBinaryOperator operator = new MgBinaryOperator();
        operator.setName(new Text(name));
        operator.setLeft(leftInput);
        operator.setRight(rightInput);
        operator.setResult(output);
        operator.getCommands().addCollectionLast(commands);
        return operator;
    }

    public static MgLunaryOperator createLunaryOperator(String name){
        MgLunaryOperator operator = new MgLunaryOperator();
        operator.setName(new Text(name));
        return operator;
    }

    public static MgLunaryOperator createLunaryOperator(
        String name,
        MgInstanceVariable input,
        MgInstanceVariable output
    ){
        return createLunaryOperator(
            name,
            input,
            output,
            new Array<>(new MgReturnCommand())
        );
    }

    public static MgLunaryOperator createLunaryOperator(
        String name,
        MgInstanceVariable input,
        MgInstanceVariable output,
        Clump<MgStandaloneCommand> commands
    ){
        MgLunaryOperator operator = new MgLunaryOperator();
        operator.setName(new Text(name));
        operator.setRight(input);
        operator.setResult(output);
        operator.getCommands().addCollectionLast(commands);
        return operator;
    }

    public static MgRunaryOperator createRunaryOperator(String name){
        MgRunaryOperator operator = new MgRunaryOperator();
        operator.setName(new Text(name));
        return operator;
    }

    public static MgRunaryOperator createRunaryOperator(
        String name,
        MgInstanceVariable input,
        MgInstanceVariable output
    ){
        return createRunaryOperator(
            name,
            input,
            output,
            new Array<>(new MgReturnCommand())
        );
    }

    public static MgRunaryOperator createRunaryOperator(
        String name,
        MgInstanceVariable input,
        MgInstanceVariable output,
        Clump<MgStandaloneCommand> commands
    ){
        MgRunaryOperator operator = new MgRunaryOperator();
        operator.setName(new Text(name));
        operator.setLeft(input);
        operator.setResult(output);
        operator.getCommands().addCollectionLast(commands);
        return operator;
    }
}
