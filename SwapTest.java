import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SwapTest{
    public static void main(String[] args){
        HashMap<Integer,Job> jobs = new HashMap<>();
        LinkedList list = new LinkedList();
        Scanner in = new Scanner(System.in);
        String str = in.next();
        while(str != "exit"){
            switch(str){
                case "jobs":
                    System.out.println(jobs.toString());
                    break;
                case "list":
                    System.out.println(list.toString());
                    break;
                case "exit":
                    break;
                default:
                    int num = in.nextInt();
                    switch (str) {
                        case ("add"):
                            int length  = in.nextInt();
                            jobs.put(num,new Job(num,length));
                            break;
                        case ("ff"):
//                            list.firstFit(new Segment();
                            break;
                        case ("nf"):
//                            list.nextFit(node, lastPosition);
                            break;
                        case ("bf"):
//                            list.bestFit(node);
                            break;
                        case ("wf"):
//                            list.worseFit(node);
                            break;
                        case("de"):
//                            list.deallocate()
                        default:
                    }
            }

        }

    }

}