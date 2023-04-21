package Contest;

import java.util.*;

public class LC2561RearrangingFruits {
    public static long minCost(int[] basket1, int[] basket2) {

        int min = Integer.MAX_VALUE;

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        Arrays.sort(basket1);
        Arrays.sort(basket2);

        min = Math.min(basket1[0], basket2[0]);

        HashMap<Integer, Integer> map = new HashMap<>();
        int p1 = 0;
        int p2 = 0;
        while (p1 < basket1.length || p2 < basket2.length) {
            if (p1 == basket1.length) {
                int val = basket2[p2];
                int num = 0;
                while (p2 < basket2.length && basket2[p2] == val) {
                    p2++;
                    num++;
                    if (num != 0 && num % 2 == 0) {
                        list2.add(val);
                    }
                }
                if (num % 2 != 0) {
                    return -1;
                }
                continue;
            } else if (p2 == basket2.length) {
                int val = basket1[p1];
                int num = 0;
                while (p1 < basket1.length && basket1[p1] == val) {
                    p1++;
                    num++;
                    if (num != 0 && num % 2 == 0) {
                        list1.add(val);
                    }
                }
                if (num % 2 != 0) {
                    return -1;
                }
                continue;
            }
            if (basket1[p1] == basket2[p2]) {
                p1++;
                p2++;
            } else if (basket1[p1] > basket2[p2]) {
                int val = basket2[p2];
                int num = 0;
                while (p2 < basket2.length && basket2[p2] == val) {
                    num++;
                    p2++;
                    if (num != 0 && num % 2 == 0) {
                        list2.add(val);
                    }
                }
                if (num % 2 != 0) {
                    return -1;
                }
            } else {
                int val = basket1[p1];
                int num = 0;
                while (p1 < basket1.length && basket1[p1] == val) {
                    p1++;
                    num++;
                    if (num != 0 && num % 2 == 0) {
                        list1.add(val);
                    }
                }
                if (num % 2 != 0) {
                    return -1;
                }
            }
        }


        long ans = 0L;
        int cnt = 0;
        p1 = 0;
        p2 = 0;
        list1.sort((a, b) -> (a - b));
        list2.sort((a, b) -> (a - b));
        while (p1 < list1.size() && p2 < list2.size()) {
            if (list1.get(p1) == list2.get(p2)) {
                list1.remove(p1);
                list2.remove(p2);
                p1++;
                p2++;
            } else if (list1.get(p1) > list2.get(p2)) {
                p2++;
            } else {
                p1++;
            }
        }
        p1 = 0;
        p2 = 0;
        int size = list1.size();
        while (cnt < size) {
            if (2 * min < Math.min(list1.get(p1), list2.get(p2))) {
                ans += 2 * min;
                cnt++;
                continue;
            }
            if (list1.get(p1) > list2.get(p2)) {
                ans += list2.get(p2++);
            } else {
                ans += list1.get(p1++);
            }
            cnt++;
        }

        return ans;
    }

    public static void main(String[] args) {
        /*
        [3350,1104,2004,1577,1365,2088,2249,1948,2621,750,31,2004,1749,3365,3350,3843,3365,1656,3168,3106,2820,3557,1095,2446,573,2464,2172,1326,2712,467,1104,1446,1577,53,2492,2638,1200,2997,3454,2492,1926,1452,2712,446,2997,2820,750,2529,3847,656,272,3873,530,1749,1743,251,3847,31,251,515,2858,126,2491]
[530,1920,2529,2317,1969,2317,1095,2249,2858,2636,3772,53,3106,2638,1267,1926,2882,515,3772,1969,3454,2446,656,2621,1365,1743,3557,1656,3447,446,1098,1446,467,2636,1088,1098,2882,1088,1326,644,3873,3843,3926,1920,2464,2088,205,1200,1267,272,925,925,2172,2491,3168,644,1452,573,1948,3926,205,126,3447]
         */
        int[] arr1 = {3350,1104,2004,1577,1365,2088,2249,1948,2621,750,31,2004,1749,3365,3350,3843,3365,1656,3168,3106,2820,3557,1095,2446,573,2464,2172,1326,2712,467,1104,1446,1577,53,2492,2638,1200,2997,3454,2492,1926,1452,2712,446,2997,2820,750,2529,3847,656,272,3873,530,1749,1743,251,3847,31,251,515,2858,126,2491};
        int[] arr2 = {530,1920,2529,2317,1969,2317,1095,2249,2858,2636,3772,53,3106,2638,1267,1926,2882,515,3772,1969,3454,2446,656,2621,1365,1743,3557,1656,3447,446,1098,1446,467,2636,1088,1098,2882,1088,1326,644,3873,3843,3926,1920,2464,2088,205,1200,1267,272,925,925,2172,2491,3168,644,1452,573,1948,3926,205,126,3447};
        System.out.println(minCost(arr1, arr2));
    }
}
