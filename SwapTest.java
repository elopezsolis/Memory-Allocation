import sun.awt.image.ImageWatched;

public class SwapTest{
    public static void main(String[] args){
        LinkedList list = new LinkedList();
        System.out.println(list.isEmtpy());
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(1,10,10,null),"ff"));
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(2,10,9,null),"ff"));
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(3,10,7,null),"ff"));
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(4,10,5,null),"ff"));
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(5,10,21,null),"ff"));
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(6,10,20,null),"ff"));
        System.out.println(list.toString());
        System.out.println(list.deallocate(3));
        System.out.println(list.toString());
        System.out.println(list.deallocate(5));
        System.out.println(list.toString());
        System.out.println(list.push(new Segment(7,10,22,null),"ff"));
        System.out.println(list.toString());


    }

}