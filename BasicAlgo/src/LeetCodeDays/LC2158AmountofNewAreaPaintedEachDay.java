package LeetCodeDays;

import java.util.TreeMap;

public class LC2158AmountofNewAreaPaintedEachDay {
    public int[] amountPainted(int[][] arr) {
        TreeMap<Integer, Integer> head = new TreeMap<>();
        TreeMap<Integer, Integer> tail = new TreeMap<>();
        int n = arr.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i ++){
            int[] cur = arr[i];

        }
        return null;
    }

    public int add(TreeMap<Integer, Integer> head, int start, int end){
        if(end <= start){
            return 0;
        }
        int ans = 0;
        int floor = head.floorKey(start);
        int ceiling = head.ceilingKey(start);
        int floorValue = floor == -1 ? 0 : head.get(floor);
        int ceilingValue = ceiling == -1 ? 0 : head.get(ceiling);
        if((floor == -1 && ceiling == -1) || (floor + floorValue < start && ceiling > end)){
            head.put(start, end - start);
            ans = end - start;
        }else if(floor == -1 || floor + floorValue < start){
            if(ceiling > end){
                head.put(start, end - start);
                ans = end - start;
            }else if(ceiling == end){
                head.put(start, end - start + ceilingValue);
                head.remove(ceiling);
                ans = end - start;
            }else{ // ceiling < end
                head.put(start, ceiling - start + ceilingValue);
                head.remove(ceiling);
                ans = ceiling - start + add(head, ceiling + ceilingValue, end);
            }
        }else if(ceiling == -1 || ceiling > end){
            // ans += add(head, start, floor);
            if(floor + floorValue < start){
                head.put(start, end - start);
                ans = end - start;
            }else if(floor + floorValue == start){
                head.put(floor, end - start + floorValue);
                ans = end - start;
            }else{ // floor + floorValue > start
                head.put(start, Math.max(end, floorValue) - floor);
                ans = Math.max(0, end - (floor + floorValue)) + add(head, start, floor);
            }
        }else{
            head.remove(ceiling);
            head.remove(floor);
            head.put(floor, (ceiling + ceilingValue - floor));
            ans += Math.max(0, ceiling - floor - floorValue) + add(head, start, floor) + add(head, ceiling + ceilingValue, end);
        }
        return ans;
    }
}
