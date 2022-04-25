package WeeklyPractice.April03;

public class ModKSubstringNumbers {

    public static int modWays1(String s, int k) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (Long.valueOf(s.substring(i, j + 1)) % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int modWays2(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        char[] ch = s.toCharArray();
        int[] cur = new int[k];
        int[] next = new int[k];
        int mod = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            cur = next;
            int remainder = (ch[i] - '0') % k;
            ans += remainder == 0 ? cur[0] + 1 : cur[k - remainder];
            cur[remainder] ++;
            next = new int[k];
            for(int j = 0; j < k; j ++){
                int index = (j * 10) % k;
                next[index] += cur[j];

            }
        }
        return ans;
    }

//    public static int modWays2(String s, int k) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        int n = s.length();
//        char[] ch = s.toCharArray();
//        int[] cur = new int[k];
//        int[] next = new int[k];
//        int mod = 0;
//        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            cur = next;
////            int remainder = (ch[i] - '0') % k;
//            mod = (mod * 10 + (ch[i] - '0')) % k;
//            ans += (mod == 0 ? 1 : 0) + cur[mod];
//            cur[mod]++;
//            next = new int[k];
//            for (int j = 0; j < k; j++) {
//                int index = (j * 10) % k;
//                next[index] += cur[j];
//
//            }
//        }
//        return ans;
//    }
    //public static int modWays2(String s, int k) {
    //    int[] cur = new int[k];
    //    // 帮忙迁移
    //    int[] next = new int[k];
    //    // 0...i 整体余几？
    //    int mod = 0;
    //    // 答案：统计有多少子串的值%k == 0
    //    int ans = 0;
    //    for (char cha : s.toCharArray()) {
    //        for (int i = 0; i < k; i++) {
    //            // i -> 10个
    //            // (i * 10) % k
    //            next[(i * 10) % k] += cur[i];
    //            cur[i] = 0;
    //        }
    //        int[] tmp = cur;
    //        cur = next;
    //        next = tmp;
    //        mod = (mod * 10 + (cha - '0')) % k;
    //        ans += (mod == 0 ? 1 : 0) + cur[mod];
    //        cur[mod]++;
    //    }
    //    return ans;
    //}


    // for test
    public static String randomNumber(int n) {
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(ans);
    }

    // for test
    public static void main(String[] args) {
//        System.out.println(modWays1("63425",4));
        int N = 18;
        int K = 20;
        int testTime = 10000;
        System.out.println("begin");
        for (int i = 0; i < testTime; i++) {
            String str = randomNumber((int) (Math.random() * N) + 1);
            int k = (int) (Math.random() * K) + 1;
            int ans1 = modWays1(str, k);
            int ans2 = modWays2(str, k);
            if (ans1 != ans2) {
                System.out.println("fuck!");
                System.out.println(str);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("end");
    }
}
