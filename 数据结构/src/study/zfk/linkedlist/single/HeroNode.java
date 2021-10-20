package study.zfk.linkedlist.single;

/**
 * @author zfk
 * @create 2021-08-16 3:46
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了重写方便，重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}
