package WeeklyPractice.May06;

import sun.font.CCharToGlyphMapper;

public class PalindromeStringNoLessKLenNoOverLapingMaxParts {
    public static int max1(String arr, int k) {
        if (arr == null || arr.length() == 0) {
            return 0;
        }
        char[] str = arr.toCharArray();
        return process(str, 0, k);
    }

    public static int process(char[] str, int index, int k) {
        if (str.length - index < k) {
            return 0;
        }
        int ans = process(str, index + 1, k);
        for (int i = index + k - 1; i < str.length; i++) {
            if (isPalindrome(str, index, i)) {
                ans = Math.max(ans, process(str, i + 1, k) + 1);
            }
        }
        return ans;
    }

    public static boolean isPalindrome(char[] str, int left, int right) {
        while (left <= right) {
            if (str[left++] != str[right--]) {
                return false;
            }
        }
        return true;
    }

    public static int max2(String string, int k) {
        if (string == null || string.length() < 0) {
            return 0;
        }
        char[] arr = string.toCharArray();
        char[] str = manacherString(string);
        int ans = 0;
        int next = 0;
        int[] p = new int[str.length];
        while ((next = manacherFind(str, p, next, k)) != -1) {
            next = str[next] == '#' ? next : next + 1;
            ans++;
        }
        return ans;
    }

    public static int manacherFind(char[] str, int[] p, int l, int k) {
        int c = l - 1;
        int r = l - 1;
        int n = str.length;
        for (int i = l; i < n; i++) {
            p[i] = r > i ? Math.min(p[2 * c - i], r - i) : 1;
            while (i - p[i] > l - 1 && i + p[i] < n && str[i - p[i]] == str[i + p[i]]) {
                if (++p[i] > k) {
                    return i + k;
                }
            }
            if (i + p[i] > r) {
                r = i + p[i];
                c = i;
            }
        }
        return -1;
    }

    public static char[] manacherString(String string) {
        char[] str = string.toCharArray();
        int n = str.length;
        char[] ans = new char[2 * n + 1];
        for (int i = 0; i < n; i++) {
            ans[2 * i + 1] = str[i];
            ans[2 * i] = '#';
        }
        ans[2 * n] = '#';
        return ans;
    }

//    public static int[] parts(char[] str) {
//        int[] ans = new int[str.length];
//        int n = str.length;
//        int R = -1;
//        int C = -1;
//        for (int i = 0; i < n; i++) {
//            if (R < i) {
//                R = i;
//                int a = 1;
//                while (i - a >= 0 && i + a < n && isPalindrome(str, i - a, i + a)) {
//                    R++;
//                    a++;
//                }
//                ans[i] = a;
//            } else {
//                int r = ans[2 * C - i];
//                if (i + r == R) {
//                    while (i + r + 1 < n && i - r - 1 >= 0 && isPalindrome(str, i + r + 1, i - r - 1)) {
//                        r++;
//                    }
//                    ans[i] = r;
//                    C = i;
//                } else {
//                    ans[i] = Math.min(ans[2 * C - i], R - i);
//                }
//            }
//        }
//        return ans;
//    }


    // 为了测试
    public static String randomString(int n, int r) {
        char[] str = new char[(int) (Math.random() * n)];
        for (int i = 0; i < str.length; i++) {
            str[i] = (char) ((int) (Math.random() * r) + 'a');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
//        String s = "adsfakfkl";
//        char[] chars = s.toCharArray();
//        char[] ans = manacherString(s);
//        for (int i = 0; i < ans.length; i++) {
//            System.out.printf(ans[i] + ", ");
//        }


        int n = 20;
        int r = 3;
        int testTime = 50000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            String str = randomString(n, r);
            int k = (int) (Math.random() * str.length()) + 1;
            int ans1 = max1(str, k);
            int ans2 = max2(str, k);
            if (ans1 != ans2) {
                System.out.println(str);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
























