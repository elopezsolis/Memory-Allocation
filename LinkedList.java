/**
 * This class implements a Linked List of Processes to go into memory
 */
public class LinkedList {
    Segment head;
    Segment lastPosition;
    final int length = 100;
    public LinkedList(){
        Segment temp = new Segment(0,0,length,null);
        this.head = temp; // Prevents head from being null.
    }

    /**
     * TODO: add check to see if the pid its a zero, right now it just tries to delete 0 by changing the pid to 0, which doesn't change anything.
     * @param pid
     * @return
     */
    public Segment deallocate(int pid){
        Segment temp = this.head;
        Segment prev = null;
        while (temp != null && temp.getPid() != pid){
            prev = temp;
            temp = temp.getNext();
        }
        if(temp != null){
            if(temp == head){
                if(temp.getNext().getPid()==0) {
                    System.out.println("head ");
                    temp.getNext().setLength(temp.getLength() + temp.getNext().getLength());
                    temp.getNext().setStart(temp.getStart());
                    head = temp.getNext();
                }else { temp.setPid(0); }
            }else if(temp.getNext() == null){
                if(prev.getPid()== 0) {
                    prev.setLength(prev.getNext().getLength() + prev.getLength());
                    prev.setNext(null);
                }else{ temp.setPid(0); }
                System.out.println("last");
            }else if(prev.getPid()!= 0 && temp.getNext().getPid() !=0) {
                temp.setPid(0);
                System.out.println("middle");
            }else{
                merge(temp,prev);
                System.out.println("left or right is a hole");
            }
            return temp;
        }else{return null;}

    }

    public void merge(Segment node, Segment prev){
        Segment temp = node;
        if(node.getNext().getPid()==0){
            node.getNext().setLength(node.getLength()+node.getNext().getLength());
            node.getNext().setStart(node.getStart());
            prev.setNext(node.getNext());
        }
        if(prev.getPid() == 0){
            prev.setLength(prev.getNext().getLength()+prev.getLength());
            prev.setNext(prev.getNext().getNext());
        }
    }

    /**
     * Next Fit keeps track of where was the last insertion and starts searching there.
     * - What if it reaches the end of the list and there was space for that size of that node at the
     * beginning fo the list ?
     * @param node
     * @param start
     * @return
     */
    public boolean nextFit(Segment node,Segment start){
        this.firstFit(node,start); return true;
    }
    /**
     * Finds the correct spot to insert node and calls insert() to add the node into the list
     * firstFit() also keeps track of the last place where the node was inserted by updating @var lastPosition. This is used for nextFit() but not for
     * firstFit()
     * @param node - node to add into the list
     * @return - TRUE if it was added into the list, FALSE if there was not a spot for the node.
     */
    public boolean firstFit(Segment node,Segment head){
        Segment temp = head;
        Segment prev = null;
        boolean completed;
        while( temp.getPid() != 0 || node.getLength() > temp.getLength()){
            prev = temp;
            temp = temp.getNext();
            if(temp == null){ break; }
        }

        if (temp!= null){
            insert(prev,temp,node);
            completed = true;
        }else{ completed = false;}

        return completed;
    }

    /**
     * Finds the smallest hole in memory where the process will fit.
     *
     * @param node - node to insert in the list
     * @return TRUE if variable low was modified, FALSE otherwise.
     */
    public boolean bestFit(Segment node){
        Segment temp = head;
        Segment bestFit = null;
        Segment bestFitPrev = null;
        Segment prev = null;
        int low = this.length+1;
        while(temp!= null){
            if(temp.getLength() < low && node.getLength()<= temp.getLength() && temp.getPid() == 0){
                        bestFit = temp;
                        bestFitPrev = prev;
                        low = bestFit.getLength();
            }
            prev = temp;
            temp = temp.getNext();
        }
        this.insert(bestFitPrev,bestFit,node);
        return !(low == this.length);
    }
    public boolean worseFit(Segment node){
        Segment temp = head;
        Segment worseFit = null;
        Segment worseFitPrev = null;
        Segment prev = null;
        int high =0;
        while(temp!= null){
            if(temp.getLength() > high && node.getLength() >= temp.getLength() && temp.getPid() == 0){
                worseFit = temp;
                worseFitPrev = prev;
                high = worseFit.getLength();
            }
            prev = temp;
            temp = temp.getNext();
        }
        this.insert(worseFitPrev,worseFit,node);
        return high!=0;
    }
    /**
     * Inserts the job, node, into the linked list
     * @param prev - the node's previous connection
     * @param temp - the space in which the node will be inserted
     * @param node -  the item to insert
     */
    public void insert(Segment prev,Segment temp,Segment node){
        if(temp == head ){
            node.setStart(0);
            temp.setStart(node.getLength());
            temp.setLength(temp.getLength()-node.getLength());
            node.setNext(temp);
            head = node;
        }else {

            if(node.getLength() != temp.getLength()){
                node.setStart(temp.getStart());
                temp.setStart(node.getStart() + node.getLength());
                temp.setLength(temp.getLength() - node.getLength());
                prev.setNext(node);
                node.setNext(temp);
            }else
            {
                temp.setPid(node.getPid());
            }

        }
    }

    /**
     * TODO: make job take care of instance where pid is 0
     * This method calls the proper algorithm specified to insert the node.
     * Checks if the the node's length is bigger than the list's
     * If the addition was completed then it subtracts the node's length from  the total size
     * @param strategy - type of algorithm to follow
     * @return - TRUE if the node was added, FALSE otherwise
     */
    public boolean push(Segment node , String strategy){
        boolean complete= false;
            switch (strategy) {
                case ("ff"):
                    complete = this.firstFit(node, this.head);
                    break;
                case ("nf"):
                    complete = this.nextFit(node, lastPosition);
                    break;
                case ("bf"):
                    complete = this.bestFit(node);
                    break;
                default:
                    System.out.println("commmand does not exist");
                    complete = false;
            }

        return complete;
    }
    public String toString(){
        Segment temp = this.head;
        String str = "";
        while(temp != null){
            str += temp.toString() + " ";
            temp = temp.getNext();
        }
        return str;
    }

}
