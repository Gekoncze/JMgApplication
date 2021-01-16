package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.MgExpressionCommand;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgInstructionConnectionService;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.expressions.MgExpressionInstructionCreationService;
import cz.mg.collections.list.List;


public class MgExpressionCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgExpressionCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(command.getExpression() == null) throw new LogicalException(command, "Missing expression.");
        if(commandContext.getNext() == null) throw new LogicalException(command, "Missing next command instruction.");

        List<MgInstruction> expressionInstructions = new List<>();
        List<MgInstanceVariable> output = MgExpressionInstructionCreationService.create(command.getExpression(), variables, expressionInstructions);
        if(output.count() > 0) throw new LogicalException(command, "Cannot ignore expression output.");
        MgInstructionConnectionService.connect(expressionInstructions, commandContext.getNext());

        return expressionInstructions;
    }
}
