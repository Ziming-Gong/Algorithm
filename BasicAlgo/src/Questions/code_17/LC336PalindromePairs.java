package Questions.code_17;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LC336PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            ans.addAll(f(words[i], i, map));
        }
        return ans;
    }

    public static List<List<Integer>> f(String s, int index, HashMap<String, Integer> map) {
        List<List<Integer>> res = new ArrayList<>();
        String reverse = reverse(s);
        Integer rest = map.get("");
        if (rest != null && rest != index && s.equals(reverse)) {
            add(res, rest, index);
            add(res, index, rest);
        }
        int[] rs = manacher(s);
        int mid = rs.length >> 1;
        for (int i = 1; i < mid; i++) {
            if (i - rs[i] == -1) {
                rest = map.get(reverse.substring(0, mid - i));
                if (rest != null && rest != index) {
                    add(res, rest, index);
                }
            }
        }
        for (int i = mid + 1; i < rs.length; i++) {
            if (i + rs[i] == rs.length) {
                rest = map.get(reverse.substring((mid << 1) - i));
                if (rest != null && rest != index) {
                    add(res, index, rest);
                }
            }
        }
        return res;

    }

    public static void add(List<List<Integer>> list, int left, int right) {
        List<Integer> l = new ArrayList<>();
        l.add(left);
        l.add(right);
        list.add(l);
    }


    public static int[] manacher(String s) {
        char[] str = manacherStr(s);
        int R = -1;
        int C = -1;
        int[] ans = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            ans[i] = R > i ? Math.min(ans[2 * C - i], R - i) : 1;
            while (i - ans[i] >= 0 && i + ans[i] < str.length) {
                if (str[i - ans[i]] != str[i + ans[i]]) {
                    break;
                }
                ans[i]++;
            }
            if (i + ans[i] > R) {
                R = i + ans[i];
                C = i;
            }
        }
        return ans;
    }

    public static char[] manacherStr(String s) {
        int N = s.length();
        char[] str = s.toCharArray();
        char[] ans = new char[2 * N + 1];
        for (int i = 0; i < N; i++) {
            ans[i * 2 + 1] = str[i];
            ans[i * 2] = '#';
        }
        ans[ans.length - 1] = '#';
        return ans;
    }

    public static String reverse(String str) {
        char[] ans = str.toCharArray();
        int N = ans.length;
        for (int i = 0; i < ans.length >> 1; i++) {
            char temp = ans[i];
            ans[i] = ans[N - i - 1];
            ans[N - i - 1] = temp;
        }
        return String.valueOf(ans);

    }

    public static void main(String[] args) {
        String str = "aaaa";
        int[] nums = manacher(str);
        for (int i = 0; i < str.length(); i++) {
            System.out.print(nums[(2 * i) + 1] / 2);
        }
    }


}
