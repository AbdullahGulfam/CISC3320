import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class Pid_Manager_Multithread {
    public static void main(String[] args) {
        Pid_Manager pidManagerMain = new Pid_Manager();
        pidManagerMain.allocate_map();
        Random randomSleepTime = new Random();

        ExecutorService pidPool = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            Pid_Manager_Thread pidThread = new Pid_Manager_Thread(i, randomSleepTime.nextInt(10000) + 1000, pidManagerMain);
            pidPool.execute(pidThread);
        }
        
        pidPool.shutdown();
    }
}