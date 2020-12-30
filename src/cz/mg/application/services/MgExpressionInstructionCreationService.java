package cz.mg.application.services;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.components.MgDefinition;
import cz.mg.application.entities.statical.components.definitions.MgProcedure;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;
import cz.mg.collections.list.List;


public class MgExpressionInstructionCreationService extends MgService {
    public static List<MgInstruction> create(MgProcedure procedure, MgDefinition output, MgExpression expression){
        //todo;
        return new List<>();
    }
}
