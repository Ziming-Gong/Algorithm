package basicAlgo.MonotoneStack;

//https://leetcode.com/problems/count-submatrices-with-all-ones/submissions/
public class CountSubmatricesWithAllOnes {
    public int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int count = 0;
        int[] height = new int[mat[0].length];
        for(int i = 0; i < mat.length; i ++){
            for(int j = 0; j < mat[0].length; j ++){
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1 ;
            }
            count += getCount(height);
        }
        return count;
    }
    public static int getCount(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] stack = new int[arr.length];
        int stackSize = 0;
        int count = 0;
        for(int i = 0; i < arr.length; i ++){
            while(stackSize != 0 && arr[stack[stackSize - 1]] >= arr[i]){//right is i
                int cur = stack[--stackSize];
                if(arr[cur] > arr[i]){
                    int left = stackSize == 0 ? -1 : stack[stackSize-1];
                    int n = (i - left - 1);
                    int down = Math.max((left == -1 ? 0 : arr[left]), arr[i]);
                    count += ((arr[cur] - down)*cal(n));
                }
            }
            stack[stackSize++] = i;
        }

        while(stackSize != 0){
            int cur = stack[--stackSize];
            int k = stackSize == 0 ? -1 : stack[stackSize-1];
            int l = (arr.length - k - 1);
            int height = k == -1 ? arr[cur]  : arr[cur] - arr[k];
            count += (height * cal(l));

        }
        return count;
    }
    public static int cal(int n){
        return ((n * (1 + n)) >> 1);
    }
}
