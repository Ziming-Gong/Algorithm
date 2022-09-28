package OA;

public class SubArraySum {

    public static int right(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int index = 0;
            while (index < n - i + 1) {
                for (int j = index; j < index + i; j++) {
                    sum += arr[j];
                }
                index++;
            }
        }
        return sum;

    }

    public static void main(String[] args) {
        int[] arr = {4,8,10,2,3};
        System.out.println(right(arr));
    }


}
