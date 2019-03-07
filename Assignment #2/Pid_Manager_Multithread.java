public class Pid_Manager_Multithread implements Runnable {
    public int pidThreadID;
    private int pidThreadSleepTime;
    private Pid_Manager pidManager;

    Pid_Manager_Multithread(int id, int time, Pid_Manager manager) {
        this.pidThreadID = id;
        this.pidThreadSleepTime = time;
        this.pidManager = manager;

        System.out.println("Creating Thread: " + this.pidThreadID);
    }
}