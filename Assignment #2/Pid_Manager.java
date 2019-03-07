import java.util.ArrayList;

/*
*   Create an API that demonstrates the functions of a pid manager. 
*   Include a min and max constant that limits pid values between 300 and 5000, as well as the following methods: int allocate_map(void), int allocate_pid(void), and void release_pid(int_pid).
*   
*   Author(s):  Abdullah Gulfam and Faris Kurtagic 
*                Abdullah was responsible for implementation of int allocate_map(void), int allocate_pid(void), and void release_pid(int_pid) methods, and writing up documentation.
*                Faris was responsible for implementation of member variables, the toString() methods, and the demo file.
*/

public class Pid_Manager {
    private final int MIN_PID = 300;            //  Minimum value of a pid
    private final int MAX_PID = 5000;           //  Maximum value of a pid
    public ArrayList<Pid> pids;                 //  Array of 'pid' objects that holds the pid value (int) and its status (boolean)

    //  Inner class for 'Pid' objects
    class Pid {
        private int value;          //  Int value of the pid (between MIN_PID and MAX_PID)
        private boolean status;     //  Boolean representation of whether or not the pid is in use (true = 'in use', false = 'not in use')

        //  Constructor method for creating 'Pid' 
        //  Takes int and boolean parameters and assigns them to 'value' and 'status', respectively
        Pid(int valueParam, boolean statusParam) {
            value = valueParam;
            status = statusParam;
        }

        //  'Setter' method to set the value of 'status'
        //  Takes a boolean parameter and assigns it to 'status'
        void setStatus(boolean statusParam){
            status = statusParam;
        }

        //  'Getter' method to get the status of the pid
        //  Returns the boolean value of 'status'
        boolean getStatus() {
            return status;
        }

        //  'Getter' method to get the value of the pid
        //  Returns the int value of 'value'
        int getValue() {
            return value;
        }
    }

    //  Creates and initializes a data structure (ArrayList) for storing pids using MIN_PID and MAX_PID
    //  Returns '-1' if unsuccessful and '1' if successful
    public int allocate_map() {
        pids = new ArrayList<>();
        for (int i = MIN_PID; i <= MAX_PID; i++) {
            Pid p = new Pid(i, false);
            pids.add(p);
        }

        if (pids == null) {
            return -1;
        } else {
            System.out.println("Pid map successfully allocated for use");
            return 1;
        }
    }

    //  Allocates and returns a pid if it isn't in use
    //  Returns 'value' of pid if successful and '-1' if if unable to allocate a pid (all pids are in use)
    public int allocate_pid() {
        for (int i = 0; i < pids.size(); i++) {
            if (!pids.get(i).getStatus()) {
                pids.get(i).setStatus(true);
                System.out.println("Pid " + pids.get(i).getValue() + " successfully allocated for use.");
                return pids.get(i).getValue();
            }
        }

        return -1;
    }

    //  Releases a pid from use
    //  Takes int parameter and changes 'status' to 'false' where 'value' is equal to the int parameter
    public void release_pid(int pidParam) {
        if (pidParam < 300 || pidParam > 5000) {
            System.out.println("Pid values must be between 300 and 5000.");
        } else {
            pids.get(pidParam - 300).setStatus(false);
            System.out.println("Pid " + pidParam + " successfully released from use.");
        }
    }

    //  Helper 'toString' method for demonstration purposes to display the results of 'Pid_Manager' method operations
    //  Returns a String representation of all pid values and their status
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < pids.size(); i++){
            stringBuilder.append('\n' + "Pid: " + pids.get(i).getValue() + ", Pid In Use?: " + pids.get(i).getStatus());
        }

        return stringBuilder.toString();
    }

    //  Helper 'toString' method for demonstration purposes to display the results of 'Pid_Manager' method operations
    //  Returns a String representation of all pid values and their status within a specific range
    //  Takes two int parameters that represent the start of the range and the end of the range 
    public String toString(int start, int end) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = start; i < end; i++){
            stringBuilder.append('\n' + "Pid: " + pids.get(i).getValue() + ", Pid In Use?: " + pids.get(i).getStatus());
        }

        return stringBuilder.toString();
    }
}