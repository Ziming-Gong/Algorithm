package LeetCodeDays;

import Questions.code_5.Hash;
import WeeklyPractice.April02.PerfectPairNumber;
import basicAlgo.mergesorted.ans;
import org.omg.CORBA.portable.ValueInputStream;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

public class LC212WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        Node head = new Node();
        for (String str : words) {
            build(str.toCharArray(), head);
        }
        int n = board.length;
        int m = board[0].length;
        List<String> ans = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (head.nexts[board[i][j] - 'a'] != null) {
                    StringBuilder sb = new StringBuilder();
                    visited[i][j] = true;
                    sb.append(board[i][j]);
                    search(board, head.nexts[board[i][j] - 'a'], set, i, j, sb, visited);
                }
            }
        }
        ans.addAll(set);
        return ans;
    }

    public void search(char[][] board, Node cur, HashSet<String> set, int x, int y, StringBuilder sb, boolean[][] visited) {
        if (cur.isEnd) {
            set.add(sb.toString());
        }
        if (cur.nexts == null) {
            return;
        }
        if (x - 1 >= 0 && cur.nexts[board[x - 1][y] - 'a'] != null && !visited[x - 1][y]) {
            visited[x - 1][y] = true;
            sb.append(board[x - 1][y]);
            search(board, cur.nexts[board[x - 1][y] - 'a'], set, x - 1, y, sb, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[x - 1][y] = false;
        }
        if (x + 1 < board.length && cur.nexts[board[x + 1][y] - 'a'] != null && !visited[x + 1][y]) {
            visited[x + 1][y] = true;
            sb.append(board[x + 1][y]);
            search(board, cur.nexts[board[x + 1][y] - 'a'], set, x + 1, y, sb, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[x + 1][y] = false;
        }
        if (y - 1 >= 0 && cur.nexts[board[x][y - 1] - 'a'] != null && !visited[x][y - 1]) {
            visited[x][y - 1] = true;
            sb.append(board[x][y - 1]);
            search(board, cur.nexts[board[x][y - 1] - 'a'], set, x, y - 1, sb, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[x][y - 1] = false;
        }
        if (y + 1 < board[0].length && cur.nexts[board[x][y + 1] - 'a'] != null && !visited[x][y + 1]) {
            visited[x][y + 1] = true;
            sb.append(board[x][y + 1]);
            search(board, cur.nexts[board[x][y + 1] - 'a'], set, x, y + 1, sb, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[x][y + 1] = false;
        }

    }

    public class Node {
        public Node[] nexts = new Node[26];
        public boolean isEnd = false;
    }

    public void build(char[] str, Node head) {
        Node cur = head;
        for (char c : str) {
            if (cur.nexts[c - 'a'] == null) {
                cur.nexts[c - 'a'] = new Node();
            }
            cur = cur.nexts[c - 'a'];
        }
        cur.isEnd = true;
    }
}
