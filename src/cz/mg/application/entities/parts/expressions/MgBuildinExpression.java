package cz.mg.application.entities.parts.expressions;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.components.definitions.MgBuildinRunnable;


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
