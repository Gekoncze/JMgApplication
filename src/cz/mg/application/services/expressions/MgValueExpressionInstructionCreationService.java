package cz.mg.application.services.expressions;

import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.instructions.MgSetValueToLocalInstruction;
import cz.mg.application.entities.parts.variables.MgExpressionVariable;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.parts.expressions.MgValueExpression;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgValueExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgValueExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getAtom() == null) throw new LogicalException(expression, "Missing atom definition.");
        if(expression.getValue() == null) throw new LogicalException(expression, "Missing value.");

        MgInstanceVariable selfOutput = new MgExpressionVariable(expression.getAtom());

        try {
            instructions.addLast(
                new MgSetValueToLocalInstruction(
                    expression.getAtom().fromText(expression.getValue()),
                    selfOutput
                )
            );
        } catch (RuntimeException e){
            throw new LogicalException(expression, "Could not convert '" + expression.getValue() + "' to " + expression.getAtom().getClass().getSimpleName() + ".");
        }

        variables.addLast(selfOutput);
        return new List<>(selfOutput);
    }
}
