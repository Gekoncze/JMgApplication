package cz.mg.application.entities.dynamical.instructions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.application.entities.buildin.atoms.bool8.MgBool8Object;
import cz.mg.application.entities.dynamical.objects.MgTask;
import cz.mg.application.entities.statical.parts.MgVariable;


public class MgSwitchInstruction extends MgInstruction {
    @Optional @Link
    private MgVariable condition;

    @Optional @Link
    private MgInstruction trueInstruction;

    @Optional @Link
    private MgInstruction falseInstruction;

    public MgSwitchInstruction() {
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
        this.trueInstruction = trueInstruction;
    }

    public MgInstruction getFalseInstruction() {
        return falseInstruction;
    }

    public void setFalseInstruction(MgInstruction falseInstruction) {
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
