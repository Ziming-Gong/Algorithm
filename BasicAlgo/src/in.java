import java.util.Scanner;

public class in {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("total");
        double g1 = sc.nextDouble();
        System.out.println("earned");
        double g2 = sc.nextDouble();
        System.out.println("percentage");
        double g3 = sc.nextDouble();
        Double grade = (g2 / g1) * g3;
        sc.close();
        System.out.println(grade);
    }
}
