package study.zfk.zhangsanTest;

/*

 */
public class Test02 {
    public static void main(String[] args) {
        Boy boy = new Boy();
        Boy boy2 = new Boy();
        boy.age = 22;
        boy.name = "zhangsan";
        boy2 = boy;
        boy2.name =  "李四";

        System.out.println(boy2.name);
        System.out.println(boy.name);
    }



}
class Boy{
    public int age;
    public String name;
}
