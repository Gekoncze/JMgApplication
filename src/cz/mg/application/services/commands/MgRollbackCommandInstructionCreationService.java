package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgRollbackInstruction;
import cz.mg.application.entities.statical.parts.commands.MgRollbackCommand;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.expressions.MgExpressionInstructionCreationService;
import cz.mg.collections.list.List;

import static cz.mg.application.services.expressions.MgExpressionInstructionCreationService.Pair;


public class MgRollbackCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgRollbackCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(command.getExpression() == null) throw new LogicalException(command, "Missing expression.");
        if(commandContext.getEnd() == null) throw new LogicalException(command, "Missing parent command instruction.");

        Pair expression = MgExpressionInstructionCreationService.createException(command.getExpression(), variables, commandContext.getEnd());
        expression.getInstructions().addLast(new MgRollbackInstruction(expression.getOutput()));
        return expression.getInstructions();
    }
}
