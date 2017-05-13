import java.util.ArrayList;

/**
 * This class implements a Linked List of Processes to go into memory
 */
public class LinkedList {
    int size;
    Segment head;
    public LinkedList(){
        Segment temp = new Segment(0,0,100,null);
        head = temp; // Prevents head from being null.
        size = 100;
    }

    public boolean firstFit(Segment node){
        Segment temp = this.head;
        Segment prev = null;
        System.out.println("head" + head);
        boolean completed = false;
        while( temp.getPid() != 0 || node.getLength() > temp.getLength()){
            prev = temp;
            temp = temp.getNext();
            if(temp == null){ break; }
        }
        System.out.println("temp"+temp);

        if (temp!= null){
            insert(prev,temp,node);
            completed = true;
        }else{ completed = false;}

        return completed;
    }
    public void insert(Segment prev,Segment temp,Segment node){
        if(temp == head ){
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
     * TODO: make job take care of instance where pid is 0
     * @param node
     * @param strategy
     * @return
     */
    public boolean push(Segment node , String strategy){
        boolean complete= false;
        if (node.getLength() < this.size) {
            switch (strategy) {
                case ("ff"):
                    complete = this.firstFit(node);
                    break;
                default:
                    complete = false;
            }
        } else {
            System.out.println("Not enough space");
            complete = false;
        }
        if (complete)
            this.size -= node.getLength();

        return complete;
    }
    public boolean isEmtpy(){
        return this.size == 100;
    }

    public String toString(){
        Segment temp = head;
        String str = "";
        while(temp != null){
            str += temp.toString() + " ";
            temp = temp.getNext();
        }
        return str;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
