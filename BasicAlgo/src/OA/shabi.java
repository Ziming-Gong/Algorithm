package OA;

import javafx.scene.shape.VLineTo;
import sun.util.resources.cldr.ta.CurrencyNames_ta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class shabi {

    static HashMap<String, Integer> priceMap = new HashMap<>();
    static HashMap<String, Integer> sizeMap = new HashMap<>();
    static HashMap<String, List<String>> makeList = new HashMap<>();

    static String targetName;

    public static void build(String s) {
        String[] strs = s.split(",");
        if (strs.length == 1) {
            targetName = strs[0];
        }
        String name = strs[0];
        if (!priceMap.containsKey(name)) {
            if (!strs[1].equals("null")) {
                priceMap.put(strs[0], Integer.valueOf(strs[1]));
            } else {
                priceMap.put(strs[0], -1);
            }
        }
        if (strs.length == 4) {
            String[] subProds = strs[3].split(";");
            int value = Integer.valueOf(strs[2]);
            List<String> cur = makeList.getOrDefault(name, new ArrayList<>());
            for (int i = 0; i < subProds.length; i++) {
                cur.add(subProds[i]);

            }
            makeList.put(name, cur);
            sizeMap.put(name, value);
        }
    }

    public static int getPrice() {
        return process(targetName);
    }

    public static int process(String cur) {
        //for make
        int p1 = Integer.MAX_VALUE;
        if (makeList.containsKey(cur)) {
            p1 = 0;
            List<String> list = makeList.get(cur);
            for (String next : list) {
                p1 += process(next);
            }
        }
        int p2 = Integer.MAX_VALUE;
        if (priceMap.containsKey(cur)) {
            p2 = priceMap.get(cur);
        }
        return Math.min(p1, p2);
    }
}
