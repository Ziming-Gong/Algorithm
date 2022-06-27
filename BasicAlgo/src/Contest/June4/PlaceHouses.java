package Contest.June4;

import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

public class PlaceHouses {
    public int MOD = 1000000007;

    public static int countHousePlacements(int N) {
        return process(N);
    }

    //来到N这个大小，能解决多少
    public static int process(int N) {
        if (N <= 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        //放
        int p1 = process(N - 2) + 1;
        int p2 = process(N - 1);
        return (p1 != 0 && p2 != 0) ? p1 * p2 : Math.max(p1, p2);
    }

    public static void main(String[] args) {
        System.out.println(countHousePlacements(2));
    }


}

