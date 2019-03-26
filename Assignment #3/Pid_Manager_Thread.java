/*
*   Create a multithreaded program that tests the program originally created in Assignment #1.    
*   Create several threads (see Pid_Manager_Multithread), make them each request a pid, put them each to sleep for a random duration of time, and finally release the pid.
*   
*   Author(s):  Abdullah Gulfam and Faris Kurtagic 
*                Abdullah was responsible for implementation of the Pid_Manager_Thread file and documentation.
*                Faris was responsible for implementation of the Pid_Manager_Multithread file.
*/

//  Import the Reentrant Lock class (a mutual exclusion lock in Java)
import java.util.concurrent.locks.ReentrantLock;

//  Implements Runnable to create a threaded program
public class Pid_Manager_Thread implements Runnable {
        private int pidThreadID;                            //  int that represents a unique ID for each thread
        private int pidThreadSleepTime;                     //  int that established how long thread will sleep
        private Pid_Manager pidManager;                     //  Pid_Manager that will facilitate allocation and release of pids
        private ReentrantLock pidThreadLock = new ReentrantLock();   //  Reetrant lock that will prevent race condition in the 'run' method
    
    //  Thread constructor method
    //  Takes int for pidThreadID, int for pidThreadSleepTime, and Pid_Manager for pidManager as parameters
    Pid_Manager_Thread(int id, int time, Pid_Manager manager) {
        this.pidThreadID = id;
        this.pidThreadSleepTime = time;
        this.pidManager = manager;

        System.out.println("Creating Thread " + this.pidThreadID);
    }

    //  Required method for implementation of thread 
    //  Allows thread to run and perform task
    @Override
    public void run() {
        pidThreadLock.lock();

        try {
            System.out.println("Running Thread " + this.pidThreadID);

            //  Pid is allocated from pidManager
            Integer pidID = pidManager.allocate_pid();

            //  If Pid was not successfully allocated, loop until it is.
            while (pidID == -1) {
                System.out.println("All pids currently in use. Attempting to reallocate...");
                pidID = pidManager.allocate_pid();
            }

            //  Put thread to sleep for some duration of time
            try {
                Thread.sleep(pidThreadSleepTime);                                 
            } catch (InterruptedException e) {
                System.out.println("Interrupting Thread " + this.pidThreadID);
            }

            //  Release Pid from thread use
            pidManager.release_pid(pidID);
            System.out.println("Stopping Thread " + this.pidThreadID);
        } finally {
            pidThreadLock.unlock();
        }
    }
}