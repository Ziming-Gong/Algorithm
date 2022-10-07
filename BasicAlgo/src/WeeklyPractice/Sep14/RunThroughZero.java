package WeeklyPractice.Sep14;

public class RunThroughZero {
    public static int maxLen1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                if (ok(s, i, j)) {
                    ans = Math.max(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans;
    }

    // 为了测试
    // 暴力方法
    public static boolean ok(String s, int l, int r) {
        if (((r - l + 1) & 1) == 1) {
            return false;
        }
        int[] cnts = new int[26];
        for (int i = l; i <= r; i++) {
            cnts[s.charAt(i) - 'a']++;
        }
        for (int cnt : cnts) {
            if ((cnt & 1) == 1) {
                return false;
            }
        }
        return true;
    }

    public static int maxLen2(String s) {
        char[] str = s.toCharArray();
        int sum1 = 0;
        int sum2 = 0;
        boolean haveOne = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 0) {
                haveOne = false;
            } else if (str[i] == 1) {
                if (!haveOne) {
                    haveOne = true;
                    sum1++;
                }else{ // haveOne
                    if(str[i - 1] != 1){
                        sum1 ++;
                    }
                }
            } else {// str[i] == 2
                if (i == 0 || str[i - 1] != 2) {
                    sum2++;
                }
            }
        }
        return sum1 + sum2 * 2;
    }

    // 为了测试
    public static String randomString(int n, int v) {
        char[] s = new char[n];
        for (int i = 0; i < n; i++) {
            s[i] = (char) ((int) (Math.random() * v) + 'a');
        }
        return String.valueOf(s);
    }

    // 为了测试
    public static void main(String[] args) {
        int n = 50;
        int v = 6;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String s = randomString(n, v);
            int ans1 = maxLen1(s);
            int ans2 = maxLen2(s);
            if (ans1 != ans2) {
                System.out.println(s);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
