
import java.util.*;

public class SwapTest{
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        HashMap<Integer,Job> jobs = new HashMap<>();
        HashMap<Integer,Boolean> jobStatus = new HashMap<>(); //TRUE if is available, FALSE if its already in list
        LinkedList list = new LinkedList();
        Scanner in = new Scanner(System.in);
        String[] arr = {""};
        while(arr[0] != "exit"){
            System.out.print(">");
            arr = in.nextLine().split(" ");
            if(arr.length ==1){
                switch(arr[0]) {
                    case "jobs":
                        for(Job val: jobs.values())
                            System.out.print(val.toString()+ " ");
                        System.out.println("");
                        break;
                    case "list":
                        System.out.println(list.toString());
                        break;
                    default:
                        System.out.println("Command does not exist");
                        break;
                }
            }else if(arr.length==2 || arr.length ==3) {
                try {
                    int num = Integer.parseInt(arr[1]);
                    if(num != 0){
                        switch (arr[0]) {
                            case "de":
                                if (list.deallocate(num) == null)
                                    System.out.println("Job not in the list");
                                else {
                                    jobStatus.put(num, true);
                                }
                                break;
                            case "add":
                                try {
                                    int length = Integer.parseInt(arr[2]);
                                    if(length != 0) {
                                        if (!jobs.containsKey(num)) {
                                            jobs.put(num, new Job(num, length));
                                            jobStatus.put(num, true);
                                        } else
                                            System.out.println("job already exist");
                                    }else{
                                        System.out.println("Length cannot be 0");}
                                } catch (Exception ex) {
                                    System.out.println("Third argument needs to be a number");
                                }
                                break;
                            default:
                                Job temp = jobs.get(num);
                                if (temp == null)
                                    System.out.println("Job does not exist");
                                else if (!jobStatus.get(num)) {
                                    System.out.println("Job already in the list");
                                } else if (list.push(new Segment(jobs.get(num)), arr[0]))
                                    jobStatus.put(num, false);
                        }
                    }
                    else{
                        System.out.println("PID cannot be 0");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Second argument needs to be a number");
                }
            }
        }
    }
}