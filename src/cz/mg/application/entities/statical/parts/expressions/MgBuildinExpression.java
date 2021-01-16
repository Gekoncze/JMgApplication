package cz.mg.application.entities.statical.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.statical.components.definitions.MgBuildinRunnable;


public class MgBuildinExpression extends MgExpression {
    @Optional @Part
    private MgBuildinRunnable runnable;

    public MgBuildinExpression() {
    }

    public MgBuildinExpression(MgBuildinRunnable runnable) {
        this.runnable = runnable;
    }

    public MgBuildinRunnable getRunnable() {
        return runnable;
    }

    public void setRunnable(MgBuildinRunnable runnable) {
        this.runnable = runnable;
    }
}
