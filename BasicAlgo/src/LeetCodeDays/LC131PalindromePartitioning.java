package LeetCodeDays;

import sun.jvm.hotspot.debugger.remote.arm.RemoteARMThread;

import java.util.ArrayList;
import java.util.List;

public class LC131PalindromePartitioning {
    public List<List<String>> partition(String s) {
        int[] arr = manacher(s.toCharArray());
        List<List<String>> ans = new ArrayList<>();
        process(ans, new ArrayList<>(), arr, s, 0);
        return ans;
    }

    public void process(List<List<String>> ans, List<String> cur, int[] arr, String str, int index) {
        if (index == arr.length) {
            List<String> co = copy(cur);
            ans.add(co);
            return;
        }
        int beginIdx = index * 2 + 1;
        int begin = index;
        for (; index < arr.length; index++) {
            int endIndex = index * 2 + 1;
            int midIndex = (beginIdx + endIndex) / 2;
            if (arr[midIndex] >= midIndex - beginIdx + 1) {
                cur.add(str.substring(begin, index));
                process(ans, cur, arr, str, index + 1);
                cur.remove(cur.size() - 1);
            }
        }


    }

    public List<String> copy(List<String> list) {
        List<String> ans = new ArrayList<>();
        for (String str : list) {
            StringBuilder sb = new StringBuilder();
            for (char c : str.toCharArray()) {
                sb.append(c);
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    public int[] manacher(char[] s) {
        char[] str = manacherStr(s);
        int N = str.length;
        int c = -1;
        int r = -1;
        int[] res = new int[N];


        for (int i = 0; i < N; i++) {
            res[i] = r > i ? Math.min(r - i, res[2 * c - i]) : 1;
            while (res[i] + i < N && i - res[i] >= 0) {
                if (str[i + res[i]] != str[i - res[i]]) {
                    break;
                }
                res[i]++;
            }
            if (res[i] + i > r) {
                r = res[i] + 1;
                c = i;
            }

        }
        return res;

    }

    public char[] manacherStr(char[] str) {
        int N = str.length;
        char[] ans = new char[N * 2 + 1];
        for (int i = 0; i < N; i++) {
            ans[i * 2] = '#';
            ans[i * 2 + 1] = str[i];
        }
        ans[ans.length - 1] = '#';
        return ans;
    }

    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(str.substring(0,1));
        System.out.println(str.substring(1,2));
        int mod = (int)1e9 + 7;
        System.out.println(mod);
    }
}
