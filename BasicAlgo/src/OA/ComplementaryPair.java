package OA;

import basicAlgo.DynamicProgramming.MinPathSum;
import basicAlgo.IndexTree.IndexTree_Code;
import jdk.nashorn.internal.codegen.CompilerConstants;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import sun.java2d.loops.GeneralRenderer;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComplementaryPair {
    public static long solve(List<String> list) {
        HashMap<Integer, Long> map = new HashMap<>();
        long ans = 0;
        for (String s : list) {
            char[] str = s.toCharArray();
            int bit = 0;
            for (char c : str) {
                bit ^= (1 << (c - 'a'));
            }
            ans += map.getOrDefault(bit, 0L);
            for (int i = 0; i < 26; i++) {
                ans += map.getOrDefault((bit ^ (1 << i)), 0L);
            }
            map.put(bit, map.getOrDefault(bit, 0L) + 1);

        }
        return ans;
    }

    public static long right(List<String> list) {
        long ans = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] fa = new int[26];
            for (char c : list.get(i).toCharArray()) {
                fa[c - 'a']++;
            }
            for (int j = i + 1; j < list.size(); j++) {
                int[] son = new int[26];
                for (char c : list.get(j).toCharArray()) {
                    son[c - 'a']++;
                }
                for (int k = 0; k < 26; k++) {
                    son[k] += fa[k];
                }
                int oddCnt = 0;
                for (int k = 0; k < 26; k++) {
                    if (son[k] != 0 && son[k] % 2 != 0) {
                        oddCnt++;
                    }
                }
                ans += oddCnt <= 1 ? 1 : 0;
            }
        }
        return ans;
    }

    public static List<String> generateString(int maxLen, int maxN) {
        int n = (int) (Math.random() * maxN) + 1;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int len = (int) (Math.random() * maxLen) + 1;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                sb.append((char) ('a' + (Math.random() * 26)));
            }

            ans.add(sb.toString());
        }
        return ans;
    }

    public static void main(String[] args) {
//        List<String> list1 = new ArrayList<>();
//        list1.add("ball");
//        list1.add("all");
//        list1.add("call");
//        list1.add("alll");
//        long a = solve(list1);
//        long b = solve(list1);
//        System.out.println();


        int maxLen = 1000;
        int maxN = 100;
        int testTime = 10000;
        for (int i = 1; i <= testTime; i++) {
            List<String> list = generateString(maxLen, maxN);
            long ans1 = solve(list);
            long ans2 = right(list);
            if (ans1 != ans2) {
                for (String str : list) {
                    System.out.println(str);
                }
                System.out.println("oops");
                break;
            }
            if(ans1 > 0){
                System.out.println(ans1);
            }
        }
        System.out.println("test end");
    }


}
