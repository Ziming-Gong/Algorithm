package basicAlgo.Window;

import java.util.LinkedList;

public class GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] result = f(gas, cost);
        for(int i = 0; i < gas.length; i ++){
            if(result[i] == true){
                return i;
            }
        }
        return -1;
    }

    public static boolean[] f(int[] gas, int[] cost){
        int N = gas.length;
        int M = N << 1;
        int[] arr = new int[M];
        for(int i = 0; i < N; i ++){
            arr[i] = gas[i] - cost[i];
            arr[i+N] = gas[i] - cost[i];
        }
        for(int i = 1; i< M; i++){
            arr[i] = arr[i] + arr[i - 1];
        }

        boolean[] result = new boolean[N];
        int R = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for( int L = 0; L < N; L ++){
            while(R - L < N){
                while(!queue.isEmpty() && arr[queue.peekLast()] >= arr[R]){
                    queue.pollLast();
                }
                queue.addLast(R);
                R ++;
            }
            if( L == 0){
                result[L] = (arr[queue.peekFirst()] >= 0);
            }else{
                result[L] = ((arr[queue.peekFirst()] - arr[L-1]) >= 0);
            }
            if(queue.peekFirst() == L){
                queue.pollFirst();
            }
        }
        return result;
    }



}
