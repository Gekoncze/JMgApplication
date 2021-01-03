package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgSetValueToLocalInstruction;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.expressions.MgValueExpression;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.collections.list.List;


public class MgValueExpressionInstructionCreationService extends MgService {
    public static List<MgVariable> create(
        MgValueExpression expression,
        List<MgVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getAtom() == null) throw new LogicalException(expression, "Missing atom definition.");
        if(expression.getValue() == null) throw new LogicalException(expression, "Missing value.");

        MgVariable selfOutput = new MgVariable();
        selfOutput.setDefinition(expression.getAtom());

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
