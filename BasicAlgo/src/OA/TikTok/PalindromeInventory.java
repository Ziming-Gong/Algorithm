package OA.TikTok;

public class PalindromeInventory {

    public static void main(String[] args) {
        int maxLen = 10000;
        int maxVal = 1000;
        int testTime = 1000;
        for(int i = 0; i < testTime; i ++){
            int[] arr = generateArr(maxLen, maxVal);
            int ans1 = solution(arr);
            int ans2 = minPalindrome(arr);
            if(ans1 != ans2){
                System.out.println("OOPS");
                break;
            }
        }
        System.out.println("test end");
    }

    public static int[] generateArr(int len, int maxVal){
        len *= Math.random();
        len ++;
        int[] res = new int[len];
        if(Math.random() < 0.5){
            res[0] = (int)(Math.random() * maxVal);
            for(int i = 1; i < len; i ++){
                res[i] = res[i - 1] + (int) (Math.random() * maxVal);
            }
        }else{
            for(int i = 0; i < len; i ++){
                res[i] = (int) (Math.random() * maxVal);
            }
        }
        return res;


    }
    public static int solution(int[] products) {
        if (products.length == 1) {
            return 0;
        }
        if (products.length == 2) {
            return products[0] <= products[1] ? products[1] - products[0] : -1;
        }
        int n = products.length;
        int left, right;
        if (n % 2 == 0) {
            right = (n / 2);
            left = right - 1;
        } else {
            left = (n / 2) - 1;
            right = (n / 2) + 1;
        }

        int diff = 0;
        while (left >= 0 && right < n) {
            int leftCur = products[left] + diff;
            int rightCur = products[right];
            if (leftCur > rightCur) {
                return -1;
            }
            diff += rightCur - leftCur;
            left--;
            right++;
        }
        return diff;
    }

    public static int minPalindrome(int[] products) {
        if (products == null || products.length <= 1) {
            return 0;
        }
        int mid = products.length/2;
        int left, right;
        if (products.length%2==0) {
            left = mid-1;
            right = mid;
        } else {
            left = mid-1;
            right = mid+1;
        }
        int pre = products[right] - products[left];
        int result = pre;
        if (pre < 0) {
            return -1;
        }
        while (left>=0 && right < products.length) {
            int cur = products[right] - products[left];
            if ( cur < pre) {
                return -1;
            }
            result = Math.max(cur, result);
            pre = cur;
            left--;
            right++;

        }
        return result;
    }
}
