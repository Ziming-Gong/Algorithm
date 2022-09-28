package Contest.Aug2;

public class LC6166LargestPalindromicNumber {
    public static String largestPalindromic(String num) {
        if (num.length() == 1) {
            return num;
        }
        char[] str = num.toCharArray();
        int[] arr = new int[10];
        for (char c : str) {
            arr[c - '0']++;
        }
        StringBuilder front = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        StringBuilder back = new StringBuilder();
        boolean have = false;
        for (int i = 9; i >= 0; i--) {
            if (arr[i] == 0 ) {
                continue;
            }
            if(i == 0 && front.length() == 0){
                continue;
            }
            if (arr[i] % 2 != 0 && mid.length() == 0) {
                mid.append(i);
            }
            for (int j = 1; j <= arr[i] / 2; j++) {

                front.append(i);
                back.append(i);
            }
        }
        if (front.length() == 0) {
            return mid.toString();
        }
        if (mid.length() != 0) {
            front.append(mid);
        }
        front.append(back.reverse());
        return front.toString();
    }

    public static void main(String[] args) {
        String str = "97231404236749078329522372611037933";
        largestPalindromic(str);
    }
}
