package cz.mg.application.services;

import cz.mg.application.entities.parts.instructions.MgDummyInstruction;
import cz.mg.application.entities.parts.instructions.MgInstruction;
import cz.mg.application.entities.types.MgProcedureType;
import cz.mg.application.entities.components.definitions.MgBinaryOperator;
import cz.mg.application.entities.components.definitions.MgLunaryOperator;
import cz.mg.application.entities.components.definitions.MgProcedure;
import cz.mg.application.entities.components.definitions.MgRunaryOperator;
import cz.mg.application.entities.parts.MgCheckpoint;
import cz.mg.application.entities.parts.MgInterface;
import cz.mg.application.entities.parts.variables.MgInstanceVariable;
import cz.mg.application.entities.parts.variables.MgInterfaceVariable;
import cz.mg.application.services.commands.CommandContext;
import cz.mg.application.services.commands.MgBlockCommandInstructionCreationService;
import cz.mg.application.services.exceptions.InternalException;
import cz.mg.application.services.exceptions.LogicalException;
import cz.mg.application.services.validation.MgInstructionValidationService;
import cz.mg.application.services.validation.MgValidator;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadonlyArray;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;


public class MgProcedureTypeService extends MgService {
    public static void create(MgProcedure procedure){
        validateInterface(procedure);
        validateOperator(procedure);

        List<MgInstanceVariable> generatedVariables = new List<>();
        List<List<MgInstruction>> instructionLists = new List<>();
        Map<MgCheckpoint, MgInstruction> checkpointInstructions = new Map<>();

        MgDummyInstruction begin = new MgDummyInstruction();
        MgDummyInstruction end = new MgDummyInstruction();
        CommandContext commandContext = new CommandContext(null, null, begin, end);

        instructionLists.addLast(
            MgBlockCommandInstructionCreationService.create(
                procedure, procedure.getCommands(), commandContext, generatedVariables
            )
        );

        for(MgCheckpoint checkpoint : procedure.getCheckpoints()){
            begin = new MgDummyInstruction();
            end = new MgDummyInstruction();
            commandContext = new CommandContext(null, null, begin, end);

            instructionLists.addLast(
                MgBlockCommandInstructionCreationService.create(
                    checkpoint, checkpoint.getCommands(), commandContext, generatedVariables
                )
            );

            checkpointInstructions.set(checkpoint, instructionLists.getLast().getFirst());
        }

        List<MgInstruction> allInstructions = new List<>();
        for(List<MgInstruction> instructions : instructionLists){
            allInstructions.addCollectionLast(instructions);
        }

        procedure.setType(new MgProcedureType(
            procedure,
            unionVariables(procedure, generatedVariables),
            new ReadonlyArray<>(allInstructions),
            checkpointInstructions
        ));

        for(List<MgInstruction> instructions : instructionLists){
            MgInstructionOptimizationService.optimize(instructions);
            MgInstructionValidationService.validate(procedure, instructions);
        }
    }

    private static void validateInterface(MgProcedure procedure){
        if(procedure.getInterface() != null){
            MgInterface interfase = procedure.getInterface();

            if(procedure.getInput().count() != interfase.getInput().count()){
                throw new LogicalException(procedure, "Procedure cannot implement that interface. Input count mismatch.");
            }

            if(procedure.getOutput().count() != interfase.getOutput().count()){
                throw new LogicalException(procedure, "Procedure cannot implement that interface. Output count mismatch.");
            }

            for(int i = 0; i < procedure.getInput().count(); i++){
                MgInstanceVariable procedureInput = procedure.getInput().get(i);
                MgInterfaceVariable interfaceInput = interfase.getInput().get(i);
                MgValidator.checkCompatibility(procedureInput, interfaceInput);
            }

            for(int i = 0; i < procedure.getOutput().count(); i++){
                MgInstanceVariable procedureOutput = procedure.getOutput().get(i);
                MgInterfaceVariable interfaceOutput = interfase.getOutput().get(i);
                MgValidator.checkCompatibility(interfaceOutput, procedureOutput);
            }
        }
    }

    private static void validateOperator(MgProcedure procedure){
        if(procedure instanceof MgBinaryOperator){
            if(procedure.getInput().count() != 2){
                throw new InternalException("Binary operator input count must be 2.");
            }

            if(procedure.getOutput().count() != 1){
                throw new InternalException("Binary operator output count must be 1.");
            }
        }

        if(procedure instanceof MgLunaryOperator){
            if(procedure.getInput().count() != 1){
                throw new InternalException("Lunary operator input count must be 1.");
            }

            if(procedure.getOutput().count() != 1){
                throw new InternalException("Lunary operator output count must be 1.");
            }
        }

        if(procedure instanceof MgRunaryOperator){
            if(procedure.getInput().count() != 1){
                throw new InternalException("Runary operator input count must be 1.");
            }

            if(procedure.getOutput().count() != 1){
                throw new InternalException("Runary operator output count must be 1.");
            }
        }
    }

    private static ReadonlyArray<MgInstanceVariable> unionVariables(MgProcedure procedure, List<MgInstanceVariable> generatedVariables){
        Array<MgInstanceVariable> variables = new Array<>(
            procedure.getInput().count() +
            procedure.getOutput().count() +
            procedure.getLocal().count() +
            generatedVariables.count()
        );

        int i = 0;

        for(MgInstanceVariable variable : procedure.getInput()){
            variables.set(variable, i);
            i++;
        }

        for(MgInstanceVariable variable : procedure.getOutput()){
            variables.set(variable, i);
            i++;
        }

        for(MgInstanceVariable variable : procedure.getLocal()){
            variables.set(variable, i);
            i++;
        }

        for(MgInstanceVariable variable : generatedVariables){
            variables.set(variable, i);
            i++;
        }

        return new ReadonlyArray<>(variables);
    }
}
