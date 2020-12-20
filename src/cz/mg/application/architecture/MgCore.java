package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;


public class MgCore {
    private static final ThreadLocal<MgCore> INSTANCE = new ThreadLocal<>();

    public static MgCore getInstance(){
        return INSTANCE.get();
    }

    @Mandatory @Link
    private MgThread thread;

    public MgCore() {
    }

    public MgThread getThread() {
        return thread;
    }

    public void setThread(MgThread thread) {
        this.thread = thread;
    }
}
