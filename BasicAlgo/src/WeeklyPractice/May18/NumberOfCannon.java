package WeeklyPractice.May18;

import java.sql.Connection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

// 来自学员问题
// 给定一个数组arr，表示从早到晚，依次会出现的导弹的高度
// 大炮打导弹的时候，如果一旦大炮定了某个高度去打，那么这个大炮每次打的高度都必须下降一点
// 1) 如果只有一个大炮，返回最多能拦截多少导弹
// 2) 如果所有的导弹都必须拦截，返回最少的大炮数量
public class NumberOfCannon {

    public static int numOfCannon(int[] arr) {
        //       angle      num
        TreeMap<Integer, Integer> cannons = new TreeMap<>();
        cannons.put(arr[0] - 1, 1);
        for (int i = 1; i < arr.length; i++) {
            Integer cur = cannons.ceilingKey(arr[i]);
            if (cur != null) {
                cannons.put(cur,cannons.get(cur) - 1);
                if(cannons.get(cur) == 0){
                    cannons.remove(cur);
                }
            }
            cannons.put(arr[i] - 1, cannons.getOrDefault(arr[i] - 1, 0) + 1);
        }
        int ans = 0;
        for(int i : cannons.values()){
            ans += i;
        }
        return ans;
    }

    public static int numOfCannon1(int[] arr) {
        // key : 某个大炮打的结尾数值
        // value : 有多少个大炮有同样的结尾数值
        // 比如：
        // 一共有A、B、C三个大炮
        // 如果A大炮此时打的高度是17，B大炮此时打的高度是7，C大炮此时打的高度是13
        // 那么在表中：
        // 7, 1
        // 13, 1
        // 17, 1
        // 如果A大炮此时打的高度是13，B大炮此时打的高度是7，C大炮此时打的高度是13
        // 那么在表中：
        // 7, 1
        // 13, 2
        TreeMap<Integer, Integer> ends = new TreeMap<>();
        for (int num : arr) {
            if (ends.ceilingKey(num + 1) == null) {
                ends.put(Integer.MAX_VALUE, 1);
            }
            int ceilKey = ends.ceilingKey(num + 1);
            if (ends.get(ceilKey) > 1) {
                ends.put(ceilKey, ends.get(ceilKey) - 1);
            } else {
                ends.remove(ceilKey);
            }
            ends.put(num, ends.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int value : ends.values()) {
            ans += value;
        }
        return ans;
    }

    public static int[] generateRandomArray(int v, int l){
        int n = (int) (Math.random() * l);
        int[] ans = new int[n];
        for(int i = 0; i < n; i ++){
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }

    public static void main(String[] arg){
        int testTime = 1000;
        int maxValue = 1000;
        int maxLength = 10000;
        System.out.println("test begin");
        for(int i = 0; i < testTime; i ++){
            int[] cannons = generateRandomArray(maxValue, maxLength);
            int ans1 = numOfCannon1(cannons);
            int ans2 = numOfCannon(cannons);
            if(ans1 != ans2){
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");

    }
}
