package Questions.code_14;

public class LC41FirstMissingPositive {
    public int firstMissingPositive(int[] arr) {
        int N = arr.length;
        int[] ans = new int[N];
        int L = 0;
        int R = N;
        while(L < R){
            if(arr[L] == L + 1){
                L ++;
            }else{
                if(arr[L] > R || arr[L] <= 0){
                    swap(arr, L, --R);
                }else if(arr[L] == arr[arr[L] - 1]){
                    swap(arr, L, --R);
                }else{
                    swap(arr, L, arr[L] - 1);
                }
            }
        }
        return L + 1;


    }

    public void swap(int[] arr, int L, int R){
        arr[L] ^= arr[R];
        arr[R] ^= arr[L];
        arr[L] ^= arr[R];
    }
}
