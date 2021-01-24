package cz.mg.application.entities.architecture.utilities;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;


public class JavaThread implements Runnable {
    @Optional @Part
    private Thread thread;

    @Mandatory @Value
    private boolean stopped;

    @Mandatory @Value
    private boolean running;

    @Mandatory @Part
    private Runnable runnable;

    public JavaThread(Runnable runnable) {
        this.runnable = runnable;
    }

    public void start(){
        running = true;
        stopped = false;

        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        stopped = true;
    }

    public boolean isStopped() {
        return stopped;
    }

    public boolean isRunning() {
        return running;
    }

    public static void snooze(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void run() {
        while(!stopped){
            runnable.run();
        }
        running = false;
    }
}
