package basicAlgo.Manacher;

public class AddShortestEnd {
    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int r = -1;
        int c = -1;
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = r > i ? Math.min(r - i, pArr[2 * c - i]) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] >= 0) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > r) {
                r = pArr[i] + i;
                c = i;
            }
            if (r == str.length) {
                max = pArr[i];
                break;
            }
        }
        char[] ans = new char[s.length() - max + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[ans.length - 1 - i] = str[2 * i + 1];
        }
        return String.valueOf(ans);

    }

    public static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] ans = new char[str.length * 2 + 1];
        for (int i = 0; i < str.length; i++) {
            ans[2 * i] = '#';
            ans[2 * i + 1] = str[i];
        }
        ans[ans.length - 1] = '#';
        return ans;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }
}
