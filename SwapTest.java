import sun.awt.image.ImageWatched;

public class SwapTest{
    public static void main(String[] args){
        LinkedList list = new LinkedList();
        System.out.println(list.push(new Segment(1,10,10,null),"bf"));
        System.out.println("List" + list.toString());
        System.out.println(list.push(new Segment(2,10,9,null),"bf"));
        System.out.println("List" + list.toString());
        System.out.println(list.push(new Segment(3,10,7,null),"bf"));
        System.out.println("List" + list.toString());
        System.out.println(list.push(new Segment(4,10,5,null),"bf"));
        System.out.println("List" + list.toString());
        System.out.println(list.push(new Segment(5,10,21,null),"bf"));
        System.out.println("List" + list.toString());
        System.out.println(list.push(new Segment(6,10,20,null),"bf"));
        System.out.println("List" + list.toString());
        System.out.println(list.push(new Segment(7,10,28,null),"bf"));
        System.out.println(list.toString());
        System.out.println(list.deallocate(2));
        System.out.println(list.toString());
        System.out.println(list.deallocate(6));
        System.out.println(list.toString());

        System.out.println("4 " + list.deallocate(4));
        System.out.println(list.toString());
        System.out.println("bf" +list.push(new Segment(55,10,5,null),"bf"));
        System.out.println(list.toString());
        System.out.println(list.deallocate(55));
        System.out.println(list.toString());
        System.out.println("ff" + list.push(new Segment(55,10,5,null),"ff"));

        System.out.println( list.toString());

//        System.out.println(list.deallocate(1));
//        System.out.println(list.toString());
//        System.out.println(list.push(new Segment(7,10,48,null),"ff"));
//        System.out.println(list.toString());
//        System.out.println(list.deallocate(7));
//        System.out.println(list.toString());


    }

}