package cz.mg.application.entities.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.application.entities.MgEntity;
import cz.mg.application.entities.architecture.utilities.JavaThread;


public class MgCore extends MgEntity implements Runnable {
    private static final ThreadLocal<MgCore> INSTANCE = new ThreadLocal<>();

    public static MgCore getInstance(){
        return INSTANCE.get();
    }

    @Mandatory @Part
    private final JavaThread javaThread;

    @Optional @Link
    private MgThread thread;

    public MgCore() {
        javaThread = new JavaThread(this);
    }

    public MgThread getThread() {
        return thread;
    }

    public void setThread(MgThread thread) {
        this.thread = thread;
    }

    public void start(){
        javaThread.start();
    }

    public void stop(){
        javaThread.stop();
    }

    public boolean isRunning(){
        return javaThread.isRunning();
    }

    @Override
    public void run() {
        INSTANCE.set(this);

        if(thread != null){
            thread.run();
        } else {
            JavaThread.snooze();
        }
    }
}
