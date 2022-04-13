package basicAlgo.KMP;

public class KMP {
    public static int getIndexOf(String s1, String s2) {
        if (s2.length() > s1.length() || s1 == null || s2 == null || s1.length() < 1) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = getNext(str2);
        int x = 0;
        int y = 0;

        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
                y = 0;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y: -1;
    }

    public static int[] getNext(char[] str) {
        if(str.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int index = 2;
        while (index < next.length) {
            if (str[cn] == str[index - 1]) {
                next[index++] = ++cn;
            } else if (cn == 0) {
                next[index++] = cn;
            } else {
                cn = next[cn];
            }
        }
        return next;

    }


    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }


}
