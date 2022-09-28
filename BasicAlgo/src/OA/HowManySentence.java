package OA;

import javafx.concurrent.WorkerStateEvent;

import java.util.Arrays;
import java.util.HashMap;

public class HowManySentence {
    public static int[] solve(String[] words, String[] sentences) {
        int n = words.length;
        int m = sentences.length;
        int[] ans = new int[m];
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            char[] cur = word.toCharArray();
            Arrays.sort(cur);
            String curS = new String(cur);
            map.put(curS, map.getOrDefault(curS, 0) + 1);
        }

        for (int i = 0; i < m; i++) {
            String s = sentences[i];
            String[] sentence = s.split(" ");
            int curA = 1;
            for (int j = 0; j < sentence.length; j++) {
                char[] cur = sentence[j].toCharArray();
                Arrays.sort(cur);
                String sort = new String(cur);
                curA *= map.getOrDefault(sort, 1);
            }
            ans[i] = curA;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"bats","tabs","in","cat","act"};
        String[] sentences = {"cat the bats", "in the act", "act tabs in"};
        int[] ans = solve(words, sentences);
        for(int i : ans){
            System.out.printf(i + ", ");
        }
    }
}
