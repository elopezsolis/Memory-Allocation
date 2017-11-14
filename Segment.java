/**
 * Segment
 * This is used to track free and used memory.
 * If the pid is 0, then its a hole in the linked list.
 *
 */
public final class Segment {
    private int pid;
    private int start;
    private int length;
    private Segment next;
    public Segment(int pid, int start, int length, Segment next) {
        this.pid = pid;
        this.start = start;
        this.length = length;
        this.next = next;
    }
    public Segment(Job job){
        this.pid = job.getPid();
        this.start = 0;
        this.length = job.getSize();
        this.next = null;
    }
    public int getPid() {
        return pid;
    }
    public int getStart() {
        return start;
    }
    public int getLength() {
        return length;
    }
    public Segment getNext() {
        return next;
    }
    public void setPid(int pid) { this.pid = pid; }
    public void setStart(int start) {
        this.start = start;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setNext(Segment next) {
        this.next = next;
    }
    @Override
    public String toString() {
        return String.format("(%d %d %d)", pid, start, length);
    }
}
