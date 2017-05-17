import java.util.*;

public class SwapTest{
    /**
     * need to handle when job is not present in the hash map
     * need to handle when job is already in list, this means that the jobs in the map don't get deleted, just copied?
     * need to handle when the user enters wrong input
     * signal user if job with the same pid is already in the jobs map, so map doesn't replace it
     * after deallocating, the job is available again from the jobs list?
     * @param args
     */
    public static void main(String[] args){
        HashMap<Integer,Job> jobs = new HashMap<>();
        LinkedList list = new LinkedList();
        Scanner in = new Scanner(System.in);
        String command;
        String[] arr = {""};
        while(arr[0] != "exit"){
            System.out.print(">");
            arr = in.nextLine().split(" ");
            command = arr[0];
            if(arr.length ==1){
                switch(command) {
                    case "jobs":
                        System.out.println(jobs.toString());
                        break;
                    case "list":
                        System.out.println(list.toString()); // fix the output of hash map
                        break;
                    default:
                        System.out.println("Commnad does not exist");
                        break;
                }
            }else if(arr.length==2 || arr.length ==3) {
                try {
                    int num = Integer.parseInt(arr[1]);
                    switch (command) {
                        case "de":
                            list.deallocate(num);
                            break;
                        case "add":
                            try {
                                int length = Integer.parseInt(arr[2]);
                                if(!jobs.containsKey(num))
                                    jobs.put(num, new Job(num, length));
                                else
                                    System.out.println("job already exist");
                            } catch (Exception ex) {
                                System.out.println("Third argument needs to be a number");
                            }
                            break;
                        default:
                            Job temp = jobs.get(num);
                            if (temp == null)
                                System.out.println("Job does not exist");
                            else
                                list.push(new Segment(jobs.get(num)), command);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Second argument needs to be a number");
                }
            }
        }
    }
}