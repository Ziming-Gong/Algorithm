package OA;

public class LC1328BreakaPalindrome {
    public String breakPalindrome(String s) {
        if (s.length() == 1) {
            return "";
        }
        char[] str = s.toCharArray();
        int n = str.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n / 2; i++) {
            if (str[i] != 'a') {
                sb.append(s.substring(0, i));
                sb.append('a');
                sb.append(s.substring(i + 1, n));
                return sb.toString();
            }
        }
        if (n % 2 != 0) {
            if (str[n / 2] != 'a') {
                sb.append(s.substring(0, n / 2));
                sb.append('a');
                sb.append(s.substring(n / 2 + 1, n));
                return sb.toString();
            }
        }
        sb.append(s.substring(0, n - 1));
        sb.append('b');
        return sb.toString();
    }
}
