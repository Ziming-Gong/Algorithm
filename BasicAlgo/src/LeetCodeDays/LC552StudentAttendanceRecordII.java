package LeetCodeDays;

public class LC552StudentAttendanceRecordII {
    public int mod = (int) 1e9 + 7;
    public int checkRecord(int n) {
        // A : 0 / L : 1 / P : 2;
        if(n == 1){
            return 3;
        }
        long a00 = 2, a01 = 2, a02 = 1, a10 = 2, a11 = 1, a12 = 0;

        for(int i = 4; i <= n; i ++){
            long t00 = a00;
            long t01 = a01;
            long t02 = a02;
            long t10 = a10;
            long t11 = a11;
            long t12 = a12;
            a00 = (t00 + t01 + t02) % mod;
            a01 = t00;
            a02 = t01;
            a10 = (t00 + t01 + t02 + t10 + t11 + t12) % mod;
            a11 = t10;
            a12 = t11;
        }




        return (int)((a00 + a01 + a02  + a10 + a11 + a12 ) % mod);
    }
    public static long M = 1000000007;
    public  static int checkRecord1(int n) {
        long a0l0 = 1;
        long a0l1 = 0, a0l2 = 0, a1l0 = 0, a1l1 = 0, a1l2 = 0;
        for (int i = 0; i < n; i++) {
            long new_a0l0 = (a0l0 + a0l1 + a0l2) % M;
            long new_a0l1 = a0l0;
            long new_a0l2 = a0l1;
            long new_a1l0 = (a0l0 + a0l1 + a0l2 + a1l0 + a1l1 + a1l2) % M;
            long new_a1l1 = a1l0;
            long new_a1l2 = a1l1;
            a0l0 = new_a0l0;
            a0l1 = new_a0l1;
            a0l2 = new_a0l2;
            a1l0 = new_a1l0;
            a1l1 = new_a1l1;
            a1l2 = new_a1l2;
        }
        return (int)((a0l0 + a0l1 + a0l2 + a1l0 + a1l1 + a1l2) % M);
    }

    public static void main(String[] args) {
        System.out.println(checkRecord1(3));
    }

}
