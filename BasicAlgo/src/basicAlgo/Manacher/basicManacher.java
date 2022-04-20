package basicAlgo.Manacher;

public class basicManacher {
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
//        System.out.println(s);
        char[] str = manacherString(s);
//        print(str);
        int c = -1;
        int r = -1;
        int[] pArr = new int[str.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = r > i ? Math.min(pArr[2 * c - i], r - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] >= 0) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > r) {
                c = i;
                r = i + pArr[i];
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;

    }

    public static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] ans = new char[str.length * 2 + 1];
        for (int i = 0; i < str.length; i++) {
            ans[2 * i + 1] = str[i];
            ans[2 * i] = '#';
        }
        ans[ans.length - 1] = '#';
        return ans;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i -1;
            int R = i + 1;
            while(L >= 0 && R < str.length && str[L] == str[R]){
                L --;
                R ++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max/2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void print(char[] str) {
        for (char c : str) {
            System.out.printf(", " + c);
        }
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }


}
