package cz.mg.application.entities.runtime.instructions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.buildin.atoms.bool8.MgBool8;
import cz.mg.application.entities.buildin.atoms.bool8.MgBool8Object;
import cz.mg.application.entities.runtime.objects.MgTask;
import cz.mg.application.entities.statical.parts.variables.MgVariable;
import cz.mg.application.services.validation.MgValidator;

import java.util.Objects;


public class MgBranchingInstruction extends MgInstruction {
    @Mandatory @Link
    private final MgVariable condition;

    @Optional @Link
    private MgInstruction trueInstruction;

    @Optional @Link
    private MgInstruction falseInstruction;

    public MgBranchingInstruction(
        MgVariable condition,
        MgInstruction trueInstruction,
        MgInstruction falseInstruction
    ) {
        MgValidator.checkCompatibility(condition, MgBool8.getInstance());
        this.condition = condition;
        setTrueInstruction(trueInstruction);
        setFalseInstruction(falseInstruction);
    }

    public MgVariable getCondition() {
        return condition;
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
