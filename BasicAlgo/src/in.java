import java.util.Scanner;

public class in {
    public static void main(String[] args) {
        for (int i = 1; i < 10000; i++) {
            int a = (int) (Math.random() * 10000);
            int b = (int) (Math.random() * 10000);
            int c = (a & b) + (a | b);
            int d = (a ^ b) + (a & b) * 2;
            if (a + b != c || a + b != d) {
                System.out.println("oops");
                break;
            }
        }
        System.out.println("test end");

    }
}
