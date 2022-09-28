package OA;

public class BalanceOrNot {


    public static int[] solve(String[] strs, int[] max) {
        int n = max.length;
        int[] ans = new int[n];
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            char[] cur = s.toCharArray();
            int v = 0;
            int canReplace = max[i];
            for (char c : s.toCharArray()) {
                if (c == '<') {
                    v++;
                } else {
                    v--;
                }
                if (v < 0) {
                    if (canReplace > 0) {
                        v = 0;
                        canReplace--;
                    } else {
                        break;
                    }
                }
            }
            ans[i] = v == 0 ? 1 : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] arr = {"<<<><><>"};
        int[] max = {2};
        int[] ans = solve(arr, max);
        print(ans);
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.printf(i + ", ");
        }
    }
}
