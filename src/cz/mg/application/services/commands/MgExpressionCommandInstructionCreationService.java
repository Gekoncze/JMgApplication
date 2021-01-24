package cz.mg.application.services.commands;

import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.parts.commands.MgExpressionCommand;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.expressions.MgExpressionInstructionCreationService;
import cz.mg.collections.list.List;

import static cz.mg.application.services.expressions.MgExpressionInstructionCreationService.Pair;


public class MgExpressionCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgExpressionCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(command.getExpression() == null) throw new LogicalException(command, "Missing expression.");
        if(commandContext.getEnd() == null) throw new LogicalException(command, "Missing parent command instruction.");

        Pair expression = MgExpressionInstructionCreationService.createVoid(
            command.getExpression(), variables, commandContext.getEnd()
        );

        return expression.getInstructions();
    }
}
