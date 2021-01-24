package cz.mg.application.entities.parts.instructions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.buildin.atoms.bool8.MgBool8Object;
import cz.mg.application.entities.objects.MgTask;
import cz.mg.application.entities.parts.variables.MgVariable;

import java.util.Objects;


public class MgBranchingInstruction extends MgInstruction {
    @Optional @Link
    private MgVariable condition;

    @Optional @Link
    private MgInstruction trueInstruction;

    @Optional @Link
    private MgInstruction falseInstruction;

    public MgBranchingInstruction(
        MgInstruction trueInstruction,
        MgInstruction falseInstruction
    ) {
        setTrueInstruction(trueInstruction);
        setFalseInstruction(falseInstruction);
    }

    public MgVariable getCondition() {
        return condition;
    }

    public void setCondition(MgVariable condition) {
        this.condition = condition;
    }

    public MgInstruction getTrueInstruction() {
        return trueInstruction;
    }

    public void setTrueInstruction(MgInstruction trueInstruction) {
        Objects.requireNonNull(trueInstruction);
        this.trueInstruction = trueInstruction;
    }

    public MgInstruction getFalseInstruction() {
        return falseInstruction;
    }

    public void setFalseInstruction(MgInstruction falseInstruction) {
        Objects.requireNonNull(falseInstruction);
        this.falseInstruction = falseInstruction;
    }

    @Override
    public void run(MgTask task) {
        boolean condition = ((MgBool8Object)task.getObject(this.condition)).getValue();
        if(condition){
            task.setInstruction(trueInstruction);
        } else {
            task.setInstruction(falseInstruction);
        }
    }
}
