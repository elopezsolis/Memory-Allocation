import sun.awt.image.ImageWatched;

public class SwapTest{
    public static void main(String[] args){
        LinkedList list = new LinkedList();
        System.out.println(list.isEmtpy());
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(2,10,10,null),"ff"));

        System.out.println(list.isEmtpy());
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(3,10,9,null),"ff"));
        System.out.println(list.isEmtpy());
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(4,10,1,null),"ff"));
        System.out.println(list.isEmtpy());
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(5,10,1,null),"ff"));
        System.out.println(list.isEmtpy());
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(6,10,80,null),"ff"));
        System.out.println(list.isEmtpy());
        System.out.println(list.toString());

    }

}