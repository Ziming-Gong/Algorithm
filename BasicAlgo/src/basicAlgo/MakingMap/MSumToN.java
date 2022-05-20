package basicAlgo.MakingMap;

public class MSumToN {
    public static boolean isMSum1(int N) {
        for (int i = 1; i < N; i++) {
            int sum = i;
            int start = i;
            while (sum <= N) {
                if (sum == N) {
                    return true;
                }
                start++;
                sum += start;
            }
        }
        return false;
    }

    public static boolean isMSum2(int N) {
//        if (N - (N & (~N + 1)) == 0) { ///也对！
//            return false;
//        } else {
//            return true;
//        }
        return (N &(N - 1)) != 0;
    }

    public static void main(String[] args) {
        for (int i = 2; i <= 260; i++) {
            System.out.println("i :" + i + " ans: " + isMSum1(i));
        }
    }


}
