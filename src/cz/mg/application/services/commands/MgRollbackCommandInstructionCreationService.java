package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.runtime.instructions.MgRollbackInstruction;
import cz.mg.application.entities.statical.parts.commands.MgRollbackCommand;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgInstructionConnectionService;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.expressions.MgExpressionInstructionCreationService;
import cz.mg.collections.list.List;


public class MgRollbackCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgRollbackCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(command.getExpression() == null) throw new LogicalException(command, "Missing expression.");
        if(commandContext.getNext() == null) throw new LogicalException(command, "Missing next command instruction.");

        List<MgInstruction> expressionInstructions = new List<>();
        List<MgInstanceVariable> output = MgExpressionInstructionCreationService.create(command.getExpression(), variables, expressionInstructions);
        if(output.count() != 1) throw new LogicalException(command, "Expected single output.");
        MgInstructionConnectionService.connect(expressionInstructions, commandContext.getNext());

        return new List<>(new MgRollbackInstruction(output.getFirst()));
    }
}
