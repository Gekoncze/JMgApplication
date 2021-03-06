package cz.mg.application.services.expressions;

import cz.mg.application.entities.parts.instructions.MgBuildinRunnableInstruction;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.expressions.MgBuildinExpression;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.collections.list.List;


public class MgBuildinExpressionInstructionCreationService extends MgService {
    public static List<MgInstanceVariable> create(
        MgBuildinExpression expression,
        List<MgInstanceVariable> variables,
        List<MgInstruction> instructions
    ){
        if(expression.getRunnable() == null) throw new InternalException("Missing buildin runnable.");
        instructions.addLast(new MgBuildinRunnableInstruction(expression.getRunnable()));
        return new List<>();
    }
}
