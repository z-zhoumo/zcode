package study.zfk.zhangsanTest;

/**
 * @create 10-02 1:40
 */
public class recurtionDieTest {
    public static void main(String[] args) {
        die();
    }

public static long i =0;
    public static void die(){
        while (true){
            System.out.print(i++);//6911    12548   9120
            die();
        }

    }
}
