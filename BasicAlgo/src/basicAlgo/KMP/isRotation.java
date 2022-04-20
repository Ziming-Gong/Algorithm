package basicAlgo.KMP;

public class isRotation {
    public static boolean isRotation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = getNext(str2);
        char[] str = new char[str1.length * 2];
        for (int i = 0; i < str1.length; i++) {
            str[i] = str1[i];
            str[i + str1.length] = str1[i];
        }
        int x = 0;
        int y = 0;
        while (x < str.length && y < str2.length) {
            if (str[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
                y = 0;
            } else {
                y = next[y];
            }
        }

        return y == str2.length ? true : false;
    }

    public static int[] getNext(char[] str) {
        if (str.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int index = 2;
        while (index < str.length) {
            if (str[index - 1] == str[cn]) {
                next[index++] = ++cn;
            } else if (cn == 0) {
                next[index++] = cn;
            } else {
                cn = next[cn];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyui";
        System.out.println(isRotation(str1, str2));

    }

}
