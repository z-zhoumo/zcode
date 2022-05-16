package study.zfk.zhangsanTest;

/**
 */
public class recurtionDieTest {
    public static void main(String[] args) {
        die();
    }

public static long i =0;
    public static void die(){
        while (true){
            System.out.print(i+++"\t");//6911    12548   9120
            die();
        }

    }
}
