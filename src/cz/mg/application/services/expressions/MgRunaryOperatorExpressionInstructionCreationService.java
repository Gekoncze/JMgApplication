package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgCreateTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgDestroyTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgEnterTaskInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.statical.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.statical.parts.expressions.MgRunaryOperatorExpression;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.list.List;

import java.util.Iterator;


public class MgRunaryOperatorExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgRunaryOperatorExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getOperator() == null) throw new LogicalException(expression, "Missing operator.");
        if(expression.getLeft() == null) throw new LogicalException(expression, "Missing left expression.");

        MgRunaryOperator operator = expression.getOperator();

        List<MgInstanceVariable> leftOutputs = MgExpressionInstructionCreationService.create(
            expression.getLeft(), variables, instructions
        );

        List<MgInstanceVariable> selfOutputs = new List<>();

        Iterator<MgInstanceVariable> leftOutputIterator = leftOutputs.iterator();
        while(leftOutputIterator.hasNext()){
            MgInstanceVariable leftSource = leftOutputIterator.next();
            MgInstanceVariable selfOutput = new MgExpressionVariable(operator.getResult().getDefinition());
            selfOutputs.addLast(selfOutput);
            variables.addLast(selfOutput);
            instructions.addLast(new MgCreateTaskInstruction(
                operator.getType(),
                new ReadonlyArray<>(leftSource)
            ));
            instructions.addLast(new MgEnterTaskInstruction());
            instructions.addLast(new MgDestroyTaskInstruction(
                new ReadonlyArray<>(selfOutput)
            ));
        }

        return selfOutputs;
    }
}
