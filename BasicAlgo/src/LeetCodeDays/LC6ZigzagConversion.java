package LeetCodeDays;

public class LC6ZigzagConversion {
    public static String convert1(String s, int numRows) {
        if (numRows == 1 ) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        char[] ans = new char[N];
        int index = 0;
        for (int i = 0; i < numRows - 1; i++) {
            int add = (numRows - i) * 2 - 2;
            int count = 1;
            for (int j = i; j < N; j += add, count++) {
                ans[index++] = str[j];
                if (count % 2 == 0) {
                    int temp = j;
                    j += (i + 1) * 2 - 2;
                    if (j < N && j != temp) {
                        ans[index++] = str[j];
                        count++;
                    }
                }

            }
        }
        for (int j = numRows - 1; j < N; j += numRows * 2 - 2) {
            ans[index++] = str[j];
        }
        return String.valueOf(ans);
    }

    public static String convert2(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    public static String generateString(int maxLen) {
        int n = (int) (Math.random() * maxLen) + 1;
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (char) ((int) (Math.random() * 26) + 'a');
        }
        return String.valueOf(ans);

    }

    public static boolean isSame(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        for(int i = 0; i < str1.length; i ++){
            if(str1[i] != str2[i]){
                return false;
            }
        }
        return true;
    }

    public static void print(String s){
        char[] str = s.toCharArray();
        for(int i = 0; i < str.length; i ++){
            System.out.printf(str[i]+",");
        }
    }

    public static void main(String[] args) {
        int maxLen = 100000;
        int maxRow = 100000;
        int testTime = 10000;
        System.out.println("test begin");
        for(int i = 1; i <= testTime; i ++){
            String str = generateString(maxLen);
            int numRows = (int) (Math.random() * maxRow) + 1;
//            print(str);
//            System.out.println(numRows);
            String ans2 = convert2(str, numRows);
            String ans1 = convert1(str, numRows);

            if(!isSame(ans1, ans2)){
                System.out.println("oops");
                break;
            }
//            System.out.println();
        }
        System.out.println("test end");
    }
}


















