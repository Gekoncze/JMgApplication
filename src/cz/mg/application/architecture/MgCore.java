package cz.mg.application.architecture;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.application.entities.dynamical.MgDynamicalEntity;


public class MgCore extends MgDynamicalEntity implements Runnable {
    private static final ThreadLocal<MgCore> INSTANCE = new ThreadLocal<>();

    public static MgCore getInstance(){
        return INSTANCE.get();
    }

    @Optional @Link
    private MgThread thread;

    @Optional @Part
    private final Thread javaThread;

    @Mandatory @Value
    private boolean running = false;

    @Mandatory @Value
    private boolean alive = true;

    public MgCore() {
        javaThread = new Thread(this);
        javaThread.start();
    }

    public MgThread getThread() {
        return thread;
    }

    public void setThread(MgThread thread) {
        this.thread = thread;
    }

    public boolean isRunning() {
        return running;
    }

    public void start(){
        running = true;
    }

    public void stop(){
        running = false;
    }

    public void destroy(){
        alive = false;
    }

    @Override
    public void run() {
        while(alive){
            if(running){
                if(thread != null){
                    thread.run();
                } else {
                    snooze();
                }
            } else {
                snooze();
            }
        }
    }

    private void snooze(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
