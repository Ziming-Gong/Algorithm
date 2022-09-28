package WeeklyPractice.July20;

import basicAlgo.BinaryTree.MaxHappy;
import basicAlgo.Recursive.PrintAllPermutations;
import com.sun.jdi.request.ModificationWatchpointRequest;
import sun.jvm.hotspot.debugger.remote.arm.RemoteARMThread;
import sun.util.resources.cldr.kk.LocaleNames_kk;

import javax.sound.midi.MidiChannel;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

public class LC347TopKFrequentElements {

    //not good so bad
    public int[] topKFrequent1(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int n = map.size();
        int[][] arr = new int[n][2];
        int index = 0;
        for (Entry<Integer, Integer> cur : map.entrySet()) {
            arr[index][0] = cur.getKey();
            arr[index++][1] = cur.getValue();
        }
        Arrays.sort(arr, (a, b) -> (b[1] - a[1]));
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i][0];
        }
        return ans;
    }


    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int cur : nums) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        int size = map.size();
        int[][] arr = new int[size][2];
        for (Entry<Integer, Integer> cur : map.entrySet()) {
            arr[--size][0] = cur.getKey();
            arr[size][1] = cur.getValue();
        }
        f(arr, 0, arr.length - 1, k);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i][0];
        }
        return ans;

    }

    public void f(int[][] arr, int L, int R, int k) {
        if (k == R - L + 1) {
            return;
        }
        swap(arr, R, L + (int) (Math.random() * (R - L + 1)));
        int pivot = partition(arr, L, R);
        if (pivot - L == k) {
            return;
        } else if (pivot - L > k) {
            f(arr, L, pivot - 1, k);
        } else {
            f(arr, pivot, R, k - pivot + L);
        }

    }

    public int partition(int[][] arr, int L, int R) {
        int left = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index][1] <= arr[R][1]) {
                index++;
            } else {
                swap(arr, ++left, index++);
            }
        }
        swap(arr, ++left, R);
        return left;


    }

    public void swap(int[][] arr, int L, int R) {
        int[] temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
    }


}















