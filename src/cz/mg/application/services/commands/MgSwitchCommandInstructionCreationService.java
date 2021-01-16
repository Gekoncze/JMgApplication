package cz.mg.application.services.commands;

import cz.mg.application.entities.runtime.instructions.MgBranchingInstruction;
import cz.mg.application.entities.runtime.instructions.MgDummyInstruction;
import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.commands.MgCaseCommand;
import cz.mg.application.entities.statical.parts.commands.MgSwitchCommand;
import cz.mg.application.entities.statical.parts.variables.MgInstanceVariable;
import cz.mg.application.services.MgService;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.expressions.MgExpressionInstructionCreationService;
import cz.mg.collections.list.List;

import static cz.mg.application.services.expressions.MgExpressionInstructionCreationService.Pair;


public class MgSwitchCommandInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgSwitchCommand command, CommandContext commandContext, List<MgInstanceVariable> variables){
        if(command.getCaseCommands().isEmpty()) throw new LogicalException(command, "Missing case commands.");
        if(commandContext.getEnd() == null) throw new LogicalException(command, "Missing parent command instruction.");

        MgDummyInstruction begin = new MgDummyInstruction();
        MgDummyInstruction end = new MgDummyInstruction();

        end.setNextInstruction(commandContext.getEnd());

        MgInstruction next = end;
        List<MgInstruction> caseCommandsInstructions = new List<>();
        for(MgCaseCommand caseCommand : reverse(command.getCaseCommands())){
            CommandContext caseCommandContext = new CommandContext(commandContext, end);
            List<MgInstruction> caseCommandInstructions = MgCaseCommandInstructionCreationService.create(
                caseCommand, caseCommandContext, variables
            );
            caseCommandsInstructions.addCollectionFirst(caseCommandInstructions);

            if(caseCommand.getExpression() != null){
                MgBranchingInstruction condition = new MgBranchingInstruction(
                    caseCommandContext.getBegin(),
                    next
                );
                caseCommandInstructions.addFirst(condition);

                Pair expression = MgExpressionInstructionCreationService.createBoolean(
                    caseCommand.getExpression(), variables, condition
                );
                caseCommandInstructions.addCollectionFirst(expression.getInstructions());

                condition.setCondition(expression.getOutput());
            }

            next = caseCommandInstructions.getFirst();
        }

        begin.setNextInstruction(caseCommandsInstructions.getFirst());

        List<MgInstruction> instructions = new List<>();
        instructions.addLast(begin);
        instructions.addCollectionLast(caseCommandsInstructions);
        instructions.addLast(end);
        return instructions;
    }

    public static List<MgCaseCommand> reverse(List<MgCaseCommand> commands){
        List<MgCaseCommand> reversed = new List<>();
        for(MgCaseCommand command : commands){
            reversed.addFirst(command);
        }
        return reversed;
    }
}
