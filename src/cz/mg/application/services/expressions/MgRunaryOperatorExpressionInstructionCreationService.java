package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.Connection;
import cz.mg.application.entities.runtime.instructions.MgCreateTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.expressions.MgRunaryOperatorExpression;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;

import java.util.Iterator;


public class MgRunaryOperatorExpressionInstructionCreationService extends MgService {
    public static List<MgVariable> create(
        MgRunaryOperatorExpression expression,
        List<MgVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getOperator() == null) throw new LogicalException(expression, "Missing operator.");
        if(expression.getLeft() == null) throw new LogicalException(expression, "Missing left expression.");

        MgRunaryOperator operator = expression.getOperator();

        List<MgVariable> leftOutputs = MgExpressionInstructionCreationService.create(
            expression.getLeft(), variables, instructions
        );

        List<MgVariable> selfOutputs = new List<>();

        Iterator<MgVariable> leftOutputIterator = leftOutputs.iterator();
        while(leftOutputIterator.hasNext()){
            // todo - check for variable compatibility
            MgVariable leftSource = leftOutputIterator.next();
            MgVariable selfOutput = new MgVariable();
            selfOutput.setDefinition(operator.getResult().getDefinition());
            selfOutputs.addLast(selfOutput);
            variables.addLast(selfOutput);
            instructions.addLast(new MgCreateTaskInstruction(
                operator.getType(),
                new Array<>(
                    new Connection(leftSource, operator.getLeft())
                )
            ));
            instructions.addLast(new MgEnterTaskInstruction());
            instructions.addLast(new MgDestroyTaskInstruction(
                new Array<>(
                    new Connection(operator.getResult(), selfOutput)
                )
            ));
        }

        return selfOutputs;
    }
}
