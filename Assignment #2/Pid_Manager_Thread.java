import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Thread.currentThread;

public class Pid_Manager_Thread implements Runnable {
    public int pidThreadID;
    private int pidThreadSleepTime;
    private Pid_Manager pidManager;

    Pid_Manager_Thread(int id, int time, Pid_Manager manager) {
        this.pidThreadID = id;
        this.pidThreadSleepTime = time;
        this.pidManager = manager;

        System.out.println("Creating Thread " + this.pidThreadID);
    }

    public void run() {
        System.out.println("Running Thread " + currentThread().getName());

        Integer pidID = pidManager.allocate_pid();

        while (pidID = -1) {
            System.out.println("All pids currently in use. Attempting to reallocate...");
            pidID = pidManager.allocate_pid();
        }

        try {
            Thread.sleep(pidThreadSleepTime);                                 
        } catch (InterruptedException e) {
            System.out.println("Interrupting Thread " + currentThread().getName());
        }

        pidManager.release_pid(pidID);
        System.out.println("Stopping Thread " + currentThread().getName());
    }
}