package basicAlgo.Recursive;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPermutations {
    public static List<String> permutations1(String str) {
        String path = "";
        List<String> ans = new ArrayList<>();
        char[] chars = str.toCharArray();
        ArrayList<Character> rest = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            rest.add(chars[i]);
        }
        process(rest, ans, path);
        return ans;
    }

    public static void process(ArrayList<Character> rest, List<String> ans, String path) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            for (int i = 0; i < rest.size(); i++) {
                char c = rest.get(i);
                rest.remove(i);
                process(rest, ans, path + String.valueOf(c));
                rest.add(i, c);
            }
        }
    }

    public static List<String> permutations2(String str) {
        String path = "";
        List<String> ans = new ArrayList<>();
        char[] chars = str.toCharArray();

        process1(chars, 0, ans);
        return ans;
    }

    public static void process1(char[] chars, int index, List<String> ans) {
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
        } else {
            for (int i = index; i < chars.length; i++) {
                swap(chars, index, i);
                process1(chars, index + 1, ans);
                swap(chars, index, i);

            }
        }
    }

    //no repeat
    public static List<String> permutation3(String string) {

        List<String> ans = new ArrayList<>();
        char[] str = string.toCharArray();
        process3(str, 0, ans);
        return ans;
    }

    public static void process3(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            boolean[] visit = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visit[str[i]]) {
                    visit[str[i]] = true;
                    swap(str, index, i);
                    process3(str, index + 1, ans);
                    swap(str, i, index);
                }
            }
        }
    }


    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


    public static void print(List<String> str) {
        for (String string : str) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) {
        String s = "aac";
        List<String> ans = permutations1(s);
        print(ans);
        System.out.println("===================");
        List<String> ans1 = permutations2(s);
        print(ans1);
        System.out.println("===================");
        List<String> ans2 = permutation3(s);
        print(ans2);
    }
}
