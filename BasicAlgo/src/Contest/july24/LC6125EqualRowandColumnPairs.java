package Contest.july24;

import java.util.HashMap;
import java.util.TreeMap;

public class LC6125EqualRowandColumnPairs {
    public static int  equalPairs(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        Node head = new Node();
        for (int i = 0; i < N; i++) {
            buildTrie(head, grid[i]);
        }
        int ans = 0;
        for (int j = 0; j < M; j++) {
            Node cur = head;
            for (int i = 0; i < N; i++) {
                if(!cur.map.containsKey(grid[i][j])){
                    break;
                }
                cur = cur.map.get(grid[i][j]);
            }
            ans += cur.size;
        }
        return ans;
    }

    public static  void buildTrie(Node head, int[] arr) {
        Node cur = head;
        for (int i = 0; i < arr.length; i++) {
            if(cur.map.containsKey(arr[i])){
                cur = cur.map.get(arr[i]);
            }else{
                cur.map.put(arr[i], new Node());
                cur = cur.map.get(arr[i]);
            }

        }
        cur.size += 1;
    }

    public static class Node {
        public HashMap<Integer, Node> map = new HashMap<>();
        public int size = 0;
    }

    public static void main(String[] args) {
        int[][] grid = {{3,1,2,2},{1,4,4,5},{2,4,2,2},{2,4,2,2}};
        System.out.println(equalPairs(grid));
    }
}
