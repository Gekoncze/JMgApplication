package cz.mg.application.architecture.utilities;

public class JavaThread extends Thread {
    public JavaThread(Runnable target) {
        super(target);
    }

    public static void snooze(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
