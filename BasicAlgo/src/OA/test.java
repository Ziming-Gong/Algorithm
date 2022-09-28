package OA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test {


    public static HashMap<String, Integer> valueMap = new HashMap<>();
    public static HashMap<String, Float> quantityMap = new HashMap<>();
    public static HashMap<String, Integer> seqMap = new HashMap<>();
    public static HashMap<String, Double> wmaMap = new HashMap<>();

    public static String solve(String str) {
        String[] strs = str.split(",");
        String key = strs[0];
        float value = Float.valueOf(strs[1]);
        int quantity = Integer.valueOf(strs[2]);
        int seqNum = Integer.valueOf(strs[3]);
        if (seqMap.containsKey(key) && seqMap.get(key) > seqNum) {
            return key;
        }
        double M = wmaMap.getOrDefault(key, 0.0);
        float Q = quantityMap.getOrDefault(key, 0.0F);
        Double newM = ((M * Q) + (value * quantity)) / (Q + quantity);
        quantityMap.put(key, quantity + Q);
        seqMap.put(key, seqNum);
        wmaMap.put(key, newM);
        return key;
    }

    public static void main(String[] args) {
        double a = 7.9;
        int str = (int) a;
        System.out.println(Integer.valueOf(str));

    }


}
