package OA;

public class PalindromeSequence {
    public static int solve(String s) {
        char[] str = s.toCharArray();
        return process(str, new StringBuilder(), 0);

    }

    public static int process(char[] str, StringBuilder sb, int index) {
        if (sb.length() == 5) {
            System.out.println(sb.toString());
            return 1;
        }
        if (index == str.length) {
            return 0;
        }
        int ans = process(str, sb, index + 1);
        if (sb.length() < 3) {
            sb.append(str[index]);
            ans += process(str, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            if (str[index] == sb.charAt(5 - sb.length() - 1)) {
                sb.append(str[index]);
                ans += process(str, sb, index + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "010011010101010";
        System.out.println(solve(s));
    }
}
