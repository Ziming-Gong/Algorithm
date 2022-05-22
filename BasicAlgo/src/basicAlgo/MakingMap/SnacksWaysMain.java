package basicAlgo.MakingMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

//https://www.nowcoder.com/questionTerminal/d94bb2fa461d42bcb4c0f2b94f5d4281

public class SnacksWaysMain {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int bag = sc.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i <N; i ++){
            arr[i] = sc.nextInt();
        }
        long ways = ways(arr, bag);
        System.out.println(ways);
        sc.close();
    }

    public static long ways(int[] arr, int bag){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] <= bag ? 2 : 1;
        }
        int mid = (arr.length - 1)/2;
        TreeMap<Long, Long> map1 = new TreeMap<>();
        long ways = process(arr, bag,0, 0, mid, map1);
        TreeMap<Long, Long> map2 = new TreeMap<>();
        ways += process(arr, bag,0, mid + 1, arr.length - 1, map2);
        TreeMap<Long, Long> rmap = new TreeMap<>();
        long sum = 0;
        for(Entry<Long, Long> entry : map2.entrySet()){
            sum += entry.getValue();
            rmap.put(entry.getKey(), sum);
        }
        for(Entry<Long, Long> entry : map1.entrySet()){
            long left = entry.getKey();
            long value = entry.getValue();
            Long rightFloor = rmap.floorKey(bag - left);
            if(rightFloor != null){
                long rightWays = rmap.get(rightFloor);
                ways += rightWays * value;
            }
        }
        return ways + 1;

    }

    public static long process(int[] arr, long bag, long rest, int start, int end, TreeMap<Long, Long> map){
        if(rest > bag){
            return 0;
        }
        if(start > end){
            if(rest != 0){
                if(!map.containsKey(rest)){
                    map.put(rest, 0L);
                }
                map.put(rest, map.get(rest)+1);
                return 1;
            }else{
                return 0;
            }
        }else{
            long ans = process(arr, bag, rest, start + 1, end, map);
            ans +=     process(arr, bag, rest + arr[start], start + 1, end, map);
            return ans;
        }
    }
}
