package study.zfk.linkedlist.josepho;

/**
 * @create 09-13 20:38
 */
public class Boy {
    private int no;

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    private Boy next;

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }


}
