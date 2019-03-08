/*
*   Create a multithreaded program that tests the program originally created in Assignment #1.    
*   Create several threads (see Pid_Manager_Multithread), make them each request a pid, put them each to sleep for a random duration of time, and finally release the pid.
*   
*   Author(s):  Abdullah Gulfam and Faris Kurtagic 
*                Abdullah was responsible for implementation of the Pid_Manager_Thread file and documentation.
*                Faris was responsible for implementation of the Pid_Manager_Multithread file.
*   
*   Assumptions:    Although the project called for a random duration of sleep for the threads, we assumed it would make sense to have some range/limit for testing purposes to ensure all threads were allocated a pid, put to sleep, and had their pid released within a reasonable time frame
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class Pid_Manager_Multithread {
    public static void main(String[] args) {
        //  Create a Pid_Manager to pass into Pid_Manager_Thread constructor
        Pid_Manager pidManagerMain = new Pid_Manager();

        //  Allocate the map for the Pid_Manager
        pidManagerMain.allocate_map();

        //  Create a random number generation to determine the duration of sleep for a thread
        Random randomSleepTime = new Random();

        //  Create a ExecutorService pool to manage 100 threads  
        ExecutorService pidPool = Executors.newFixedThreadPool(100);

        //  Loop through pool, create a thread by passing a unique id, a random duration of sleep between 1000 and 10000 milliseconds, and a Pid_Manager 
        //  Execute each thread in the pool
        for (int i = 0; i < 100; i++) {
            Pid_Manager_Thread pidThread = new Pid_Manager_Thread(i, randomSleepTime.nextInt(10000) + 1000, pidManagerMain);
            pidPool.execute(pidThread);
        }
        
        //  Shut the ExecutorService down after executing 100 threads
        pidPool.shutdown();
    }
}