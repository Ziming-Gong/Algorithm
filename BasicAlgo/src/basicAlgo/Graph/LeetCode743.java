package basicAlgo.Graph;

public class LeetCode743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        int size = times.length;
        int[] distance = new int [n+1];
        boolean[] visit = new boolean[n+1];
        int sum = 0;
        visit[k] = true;
        for(int i = 0; i < size; i ++){
            if(times[i][0] == k){
                int index = times[i][1];
                distance[index] = times[i][2];
            }
        }
        int nextIndex = getUnselectandMinDistanceIndex(distance, visit);
        while(nextIndex != -1){
            visit[nextIndex] = true;
            for( int i = 0; i < size; i ++){
                if(times[i][0] == nextIndex+1 && !visit[times[i][1]-1]){
                    int index = times[i][1]-1;
                    distance[index] = distance[index] == 0 ? times[i][2] + distance[nextIndex] : Math.min(distance[index], times[i][2] + distance[nextIndex]);
                }
            }
            nextIndex = getUnselectandMinDistanceIndex(distance, visit);
        }
        for( int i = 0; i < n; i ++){
            sum = Math.max(sum,distance[i]);
        }
        for( int i = 0; i < visit.length; i ++){
            System.out.print(visit[i]);
            if(visit[i] == false){
                return -1;
            }
        }
        return sum;
    }

    public static int getUnselectandMinDistanceIndex(int[] distance, boolean[] visit){
        int index = -1;
        int minDistance = Integer.MAX_VALUE;
        for(int i = 0; i < distance.length; i ++){
            if(!visit[i] && distance[i] > 0 && distance[i] < minDistance){
                index = i;
                minDistance = distance[i];
            }
        }
        return index;
    }
}
