package cz.mg.application.entities.buildin;

import cz.mg.application.entities.components.MgDefinition;
import cz.mg.application.entities.components.definitions.MgBinaryOperator;
import cz.mg.application.entities.components.definitions.MgBuildinRunnable;
import cz.mg.application.entities.parts.commands.MgExpressionCommand;
import cz.mg.application.entities.parts.commands.MgReturnCommand;
import cz.mg.application.entities.parts.expressions.MgBuildinExpression;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.collections.text.Text;


public abstract class MgBuildinBinaryOperator extends MgBinaryOperator implements MgBuildinRunnable {
    public MgBuildinBinaryOperator() {
        MgInstanceVariable left = new MgInstanceVariable();
        left.setDefinition(getLeftInputDefinition());
        left.setName(new Text("left"));
        setLeft(left);

        MgInstanceVariable right = new MgInstanceVariable();
        right.setDefinition(getRightInputDefinition());
        right.setName(new Text("right"));
        setRight(right);

        MgInstanceVariable result = new MgInstanceVariable();
        result.setDefinition(getOutputDefinition());
        result.setName(new Text("result"));
        setResult(result);

        getCommands().addLast(new MgExpressionCommand(new MgBuildinExpression(this)));
        getCommands().addLast(new MgReturnCommand());
    }

    protected abstract MgDefinition getLeftInputDefinition();
    protected abstract MgDefinition getRightInputDefinition();
    protected abstract MgDefinition getOutputDefinition();
}
