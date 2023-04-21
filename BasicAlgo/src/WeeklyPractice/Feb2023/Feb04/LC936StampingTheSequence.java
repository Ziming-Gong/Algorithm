package WeeklyPractice.Feb2023.Feb04;

import sun.util.resources.in.CurrencyNames_in_ID;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC936StampingTheSequence {
    public int[] movesToStamp(String s1, String s2) {
        List<List<Integer>> graph = new ArrayList<>();

        char[] stamp = s1.toCharArray();
        char[] target = s2.toCharArray();
        int n = stamp.length;
        int m = target.length;
        int[] count = new int[m];
        int[] queue = new int[m];
        int l = 0, r = 0;
        boolean[] visited = new boolean[m];

        for (int i = 0; i <= m - 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i <= m - n; i++) {
            for (int j = 0; j < n; j++) {
                if (stamp[j] == target[i + j]) {
                    continue;
                }
                count[i]++;
                graph.get(i + j).add(i);
            }
            if (count[i] == 0) {
                queue[r++] = i;
            }
        }

        int[] temp = new int[m];
        int size = 0;
        while (l != r) {
            int index = queue[l++];
            temp[size++] = index;
            for (int j = index; j < index + n; j++) {
                for (int i = 0; i < graph.get(j).size(); i++) {
                    if(visited[i]){
                        continue;
                    }
                    count[graph.get(j).get(i)]--;
                    if (count[graph.get(j).get(i)] == 0 && !visited[graph.get(j).get(i)]) {
                        queue[r++] = i;
                        visited[i] = true;
                    }
                }
            }
        }
        for (int i = 0; i <= m - n; i++) {
            if (!visited[i]) {
                return null;
            }
        }
        int[] ans = new int[r];
        for (int i = 0; i < r; i++) {
            ans[i] = temp[r - 1 - i];
        }
        return ans;

    }
}
