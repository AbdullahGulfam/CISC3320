public class Pid_Manager_Demo {
    public static void main(String[] args) {
        //  Create a 'Pid_Manager' object and initialize it 
        Pid_Manager pidManager = new Pid_Manager();
        
        //  Initializes the map
        pidManager.allocate_map();

        //  Variable to hold a generated pid; used for later testing
        int pidTemp = 0;

        //  Allocate 10 pids so that they are in use (pids 300 to 309)
        for (int i = 0; i < 10; i++) {
            pidTemp = pidManager.allocate_pid();
        }
        //  Release a pid (309, in this case)
        pidManager.release_pid(pidTemp);

        //  Output the pids and their status (pids 300 to 319)
        //  Removing parameters ('0, 20') will output all pids and their status
        System.out.println(pidManager.toString(0, 20));
    }
}