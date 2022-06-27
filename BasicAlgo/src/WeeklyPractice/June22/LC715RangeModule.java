package WeeklyPractice.June22;

import java.util.*;

public class LC715RangeModule {
    class RangeModule1 {
        public TreeMap<Integer, Integer> map;

        public RangeModule1() {
            map = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (start == null && end == null) {
                map.put(left, right);
            } else if (start == null || map.get(start) < left) {
                map.put(left, Math.max(right, map.get(end)));
            } else { //start != null && end != null
                map.put(Math.min(start, left), Math.max(right, map.get(end)));
            }

            Map<Integer, Integer> submap = map.subMap(left, false, right, true);
            HashSet<Integer> set = new HashSet<>(submap.keySet());
            map.keySet().removeAll(set);
        }


        public boolean queryRange(int left, int right) {
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (start == null) {
                return false;
            }
            return map.get(start) >= right;
        }

        public void removeRange(int left, int right) {
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);

            if (end != null && map.get(end) > right) {
                map.put(right, map.get(end));
            }
            if (start != null && map.get(start) > left) {
                map.put(start, left);
            }
            Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
            HashSet<Integer> set = new HashSet<>(subMap.keySet());
            map.keySet().removeAll(set);
        }
    }

    public class RangeModule {
        public TreeMap<Integer, Integer> map;

        public RangeModule() {
            map = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (start == null && end == null) {
                map.put(left, right);
            } else if (start == null && end != null) {
                map.put(left, Math.max(right, map.get(end)));
            } else { //start != null && end != null
                if (start == end) {
                    if (map.get(start) < left) {
                        map.put(left, right);
                    } else {
                        map.put(start, Math.max(right, map.get(start)));
                    }
                } else { // start != end;
                    if (map.get(start) < left) {
                        map.put(left, Math.max(map.get(end), right));
                    } else {
                        map.put(start, Math.max(right, map.get(end)));
                    }
                }
            }

            Map<Integer, Integer> submap = map.subMap(left, true, right, false);
            HashSet<Integer> set = new HashSet<>(submap.keySet());
            map.remove(set);
        }


        public boolean queryRange(int left, int right) {
            // [34, 76) 整体被你的结构，有没有包含！
            // <=34 开头都没！
            Integer start = map.floorKey(left);
            if (start == null)
                return false;
            // [34, 76) 整体被你的结构，有没有包含！
            // <=34 开头有！[17，~ 60) [60 ~ 76)
            return map.get(start) >= right;
        }

        public void removeRange(int left, int right) {
            if (right <= left) {
                return;
            }
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (end != null && map.get(end) > right) {
                map.put(right, map.get(end));
            }
            if (start != null && map.get(start) > left) {
                map.put(start, left);
            }
            Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
            Set<Integer> set = new HashSet<>(subMap.keySet());
            map.keySet().removeAll(set);
        }

    }
}
