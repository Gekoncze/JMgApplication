package cz.mg.application.services.expressions;

import cz.mg.application.entities.runtime.instructions.MgInstruction;
import cz.mg.application.entities.statical.parts.MgVariable;
import cz.mg.application.entities.statical.parts.expressions.MgExpression;
import cz.mg.application.entities.statical.parts.expressions.MgGroupExpression;
import cz.mg.application.services.MgService;
import cz.mg.collections.list.List;


public class MgGroupExpressionInstructionCreationService extends MgService {
    public static List<MgVariable> create(
        MgGroupExpression expression,
        List<MgVariable> variables,
        List<MgInstruction> instructions
    ){
        List<MgVariable> outputs = new List<>();
        for(MgExpression groupExpression : expression.getExpressions()){
            outputs.addCollectionLast(
                MgExpressionInstructionCreationService.create(groupExpression, variables, instructions)
            );
        }
        return outputs;
    }
}
