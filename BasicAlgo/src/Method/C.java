package Method;

public class C {

    //排列组合！！！！撞死也得记住

    public static long c(int a, int b) {
        if (a == b) {
            return 1;
        }
        long x = 1;
        long y = 1;
        for (int i = b + 1, j = 1; i <= a; i++, j++) {
            x *= i;
            y *= j;
            long gcd = gcd(x, y);
            x /= gcd;
            y /= gcd;
        }
        return x / y;
    }

    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public static void main(String[] args) {
        System.out.println(c(100,7));
    }
}
