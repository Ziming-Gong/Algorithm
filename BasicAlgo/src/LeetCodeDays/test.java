package LeetCodeDays;

import java.util.*;

public class test {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                ArrayList<Integer> list = map.getOrDefault(routes[i][j], new ArrayList<>());
                list.add(i);
                map.put(routes[i][j], list);
            }
        }
        int ans = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> ridesVisited = new HashSet<>();
        while (!queue.isEmpty()) {
            Queue<Integer> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                int curP = queue.poll();
                for (int place : map.get(curP)) {
                    if (!ridesVisited.contains(place)) {
                        ridesVisited.add(place);
                        for (int i = 0; i < routes[place].length; i++) {
                            if (!visited.contains(routes[place][i])) {
                                visited.add(routes[place][i]);
                                temp.add(routes[place][i]);
                                if (routes[place][i] == target) {
                                    return ans;
                                }
                            }
                        }
                    }


                }
            }
            queue = temp;
            ans++;
        }
        return -1;
    }
}
