package WeeklyPractice.June29;

public class LC774MinimizeMaxDistancetoGasStation {
    public double minmaxGasDist(int[] stations, int k) {
        double accuracy = 0.0000001D;
        double l = 0;
        double r = 100000000D;
        double m = 0;
        double ans = 0;
        while (l + r > accuracy) {
            m = (l + r) / 2;
            if (ok(stations, m, k)) {
                r = m;
                ans = m;
            } else {
                l = m;
            }
        }
        return ans;

    }

    public boolean ok(int[] arr, double limit, int k) {
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans += (int) ((arr[i] - arr[i + 1]) / limit);
            if (ans > k) {
                return false;
            }
        }
        return true;

    }
}
