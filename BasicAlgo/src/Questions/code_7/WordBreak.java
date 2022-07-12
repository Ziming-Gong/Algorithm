package Questions.code_7;


import java.util.ArrayList;
import java.util.HashSet;

/*
 *
 * 假设所有字符都是小写字母. 大字符串是str. arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次.
 * 使用arr中的单词有多少种拼接str的方式. 返回方法数.
 *
 */
public class WordBreak {
    public static int ways1(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        HashSet<String> set = new HashSet<>();
        for (String s : arr) {
            set.add(s);
        }
        return process(str, 0, set);
    }

    // Str index以后有多少种方法
    public static int process(String str, int index, HashSet<String> set) {
        if (index == str.length()) {
            return 1;
        }
        int ways = 0;
        for (int i = index; i < str.length(); i++) {
            if (set.contains(str.substring(index, i + 1))) {
                ways += process(str, i + 1, set);
            }
        }
        return ways;
    }

    public static int ways2(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        HashSet<String> set = new HashSet<>();
        for (String s : arr) {
            set.add(s);
        }
        int N = str.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            int ways = 0;
            for (int end = i; end < N; end++) {
                if (set.contains(str.substring(i, end + 1))) {
                    ways += dp[end + 1];
                }
            }
            dp[i] = ways;
        }
        return dp[0];
    }

    public static class Node {
        public boolean isEnd;
        public Node[] nexts;

//        public Node(boolean i) {
//            isEnd = i;
//            nexts = new Node[26];
//        }

        public Node() {
            isEnd = false;
            nexts = new Node[26];
        }
    }

    public static class wordTier {
        public Node head;

        public wordTier() {
            head = new Node();
        }

        public void add(String str) {
            Node cur = head;
            for (int i = 0; i < str.length(); i++) {
                int c = str.charAt(i) - 'a';
                if (cur.nexts[c] == null) {
                    cur.nexts[c] = new Node();
                }
                cur = cur.nexts[c];
            }
            cur.isEnd = true;
        }

        public ArrayList<Integer> search(String str) {
            ArrayList<Integer> ans = new ArrayList<>();
            Node cur = head;
            for (int index = 0; index < str.length(); index++) {
                if (cur.nexts[str.charAt(index) - 'a'] == null) {
                    break;
                }
                cur = cur.nexts[str.charAt(index) - 'a'];
                if (cur.isEnd) {
                    ans.add(index);
                }
            }
            return ans;
        }
    }

    public static int ways3(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        wordTier wt = new wordTier();
        for (String s : arr) {
            wt.add(s);
        }
        return process(str, 0, wt);
    }

    public static int process(String str, int index, wordTier wt) {
        if (index == str.length()) {
            return 1;
        }
        int ways = 0;
        for (int end : wt.search(str.substring(index))) {
            ways += process(str, end + index + 1, wt);
        }
        return ways;
    }

    public static int ways4(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        int N = str.length();
        wordTier wt = new wordTier();
        for (String s : arr) {
            wt.add(s);
        }
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            int ways = 0;
            for (int end : wt.search(str.substring(i))) {
                ways += dp[i + end + 1];
            }
            dp[i] = ways;
        }
        return dp[0];
    }


    // 以下的逻辑都是为了测试
    public static class RandomSample {
        public String str;
        public String[] arr;

        public RandomSample(String s, String[] a) {
            str = s;
            arr = a;
        }
    }

    // 随机样本产生器
    public static RandomSample generateRandomSample(char[] candidates, int num, int len, int joint) {
        String[] seeds = randomSeeds(candidates, num, len);
        HashSet<String> set = new HashSet<>();
        for (String str : seeds) {
            set.add(str);
        }
        String[] arr = new String[set.size()];
        int index = 0;
        for (String str : set) {
            arr[index++] = str;
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < joint; i++) {
            all.append(arr[(int) (Math.random() * arr.length)]);
        }
        return new RandomSample(all.toString(), arr);
    }

    public static String[] randomSeeds(char[] candidates, int num, int len) {
        String[] arr = new String[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            char[] str = new char[(int) (Math.random() * len) + 1];
            for (int j = 0; j < str.length; j++) {
                str[j] = candidates[(int) (Math.random() * candidates.length)];
            }
            arr[i] = String.valueOf(str);
        }
        return arr;
    }

    public static void main(String[] args) {

//        String str1 = "aacaba";
//        String[] arr = {"OA.MaxNumDinstinctNum.a", "aa", "aab", "aac", "ac", "acca"};
//        System.out.println(ways3(str1, arr));


        char[] candidates = {'a', 'b'};
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 30000;
        boolean testResult = true;
        for (int i = 0; i < testTimes; i++) {
            RandomSample sample = generateRandomSample(candidates, num, len, joint);
            int ans1 = ways1(sample.str, sample.arr);
            int ans2 = ways2(sample.str, sample.arr);
            int ans3 = ways3(sample.str, sample.arr);
            int ans4 = ways4(sample.str, sample.arr);
            if (ans1 != ans2 || ans3 != ans4 || ans2 != ans4) {
                testResult = false;
            }
        }
        System.out.println(testTimes + "次随机测试是否通过：" + testResult);
    }

}
