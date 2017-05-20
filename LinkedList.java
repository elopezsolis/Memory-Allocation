/**
 * This class implements a Linked List of Processes to go into memory
 */
public class LinkedList {
    Segment head;
    Segment lastPosition;
    Segment prevTempNode;
    final int length = 100;
    public LinkedList(){
        Segment temp = new Segment(0,0,length,null);
        this.head = temp; // Prevents head from being null.
        lastPosition = head;
        prevTempNode = null;
    }

    /**
     *Deallocate the specified pid in the list. Calling method checks if the PID is 0
     *
     * @param pid the PID to delete from the list
     * @return - the Segment that is deleting from the list, Null if the segment was not found
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
                    temp.getNext().setLength(temp.getLength() + temp.getNext().getLength());
                    temp.getNext().setStart(temp.getStart());
                    head = temp.getNext();
                }else { temp.setPid(0); }
            }else if(temp.getNext() == null){
                if(prev.getPid()== 0) {
                    prev.setLength(prev.getNext().getLength() + prev.getLength());
                    prev.setNext(null);
                }else{ temp.setPid(0); }
            }else if(prev.getPid()!= 0 && temp.getNext().getPid() !=0) {
                temp.setPid(0);
            }else{
                merge(temp,prev);
            }
            return temp;
        }else{return null;}

    }

    /**
     * In charge of merging two empty processes if the pid on the right and/or the pid on the left is 0
     * @param node - process with a pid of 0
     * @param prev - previous node of the proces to merge.
     */
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
     * nextFit() uses firstFit() with different start and stop
     * this helps keeps track of where to start searching.
     * It uses stop in firstFit() to tell it when to stop searching the list. If it reaches the end, null, then it will
     * restart at head and will go until the last place where there was an addition, stop.
     * @param node - Job to add to the list
     * @param start - Where firstFit() will start searching for a space to add node.
     * @return - TRUE if it was added, FALSE otherwise
     */
    public boolean nextFit(Segment node,Segment start){
        if(this.firstFit(node,start,null)){
            return true;
        }else{
            if(this.firstFit(node,this.head,lastPosition))
                return true;
            else return false;
        }
    }
    /**
     * Finds the the first spot to insert the node and calls insert() to add the node into the list
     * firstFit() also keeps track of the last place where the node was inserted by updating @var lastPosition.
     * This is used for nextFit() but not for firstFit().
     * @param node - node to add into the list
     * @param start - the node where firstFit will start searching for any spots to add node, it will always start at the
     *             head in when calling "ff n" in command line.
     * @param stop - the node where firstFit will stop searching for space to add node, used mainly by nextFit(),
     *             its always null for firstFit().
     * @return - TRUE if it was added into the list, FALSE if there was not a spot for the node.
     */
    public boolean firstFit(Segment node,Segment start,Segment stop){
        Segment temp = start;
        Segment prev = prevTempNode;
        boolean completed;
        while( temp.getPid() != 0 || node.getLength() > temp.getLength()){
            prev = temp;
            temp = temp.getNext();
            if(temp == stop){ break; }
        }

        if (temp!= stop){
            lastPosition = temp;
            insert(prev,temp,node);
            prevTempNode = node;
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
        if(bestFit != null){
            this.insert(bestFitPrev,bestFit,node);
            return true;
        }else
            return false;
    }

    public boolean worseFit(Segment node){
        Segment temp = head;
        Segment worseFit = null;
        Segment worseFitPrev = null;
        Segment prev = null;
        int high =0;
        while(temp!= null){
            if(temp.getLength() > high && node.getLength() <= temp.getLength() && temp.getPid() == 0){
                worseFit = temp;
                worseFitPrev = prev;
                high = worseFit.getLength();
            }
            prev = temp;
            temp = temp.getNext();
        }
        if(worseFit != null){
            this.insert(worseFitPrev,worseFit,node);
            return true;
        }else
            return false;

    }
    /**
     * Inserts the job, node, into the linked list, Calling function makes sure temp it is not null
     * @param prev - the node's previous connection
     * @param temp - the space in which the node will be inserted
     * @param node -  the item to insert
     */
    public void insert(Segment prev,Segment temp,Segment node){
        if(node.getLength() == temp.getLength())
            temp.setPid(node.getPid());
        else if(temp == head ){
            node.setStart(0);
            temp.setStart(node.getLength());
            temp.setLength(temp.getLength()-node.getLength());
            node.setNext(temp);
            head = node;
        }else {
            node.setStart(temp.getStart());
            temp.setStart(node.getStart() + node.getLength());
            temp.setLength(temp.getLength() - node.getLength());
            prev.setNext(node);
            node.setNext(temp);
        }
    }

    /**
     * This method calls the proper algorithm specified to insert the node.
     * Checks if the the node's length is bigger than the list's
     * If the addition was completed then it subtracts the node's length from  the total size
     * @param strategy - type of algorithm to follow
     * @return - TRUE if the node was added, FALSE otherwise
     */
    public boolean push(Segment node , String strategy){
        boolean complete;
            switch (strategy) {
                case("ff"): complete = this.firstFit(node, this.head, null);
                    break;
                case("nf"):complete = this.nextFit(node, lastPosition);
                    break;
                case("bf"): complete = this.bestFit(node);
                    break;
                case("wf"): complete = this.worseFit(node);
                    break;
                default:
                    System.out.println("command does not exist");
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
