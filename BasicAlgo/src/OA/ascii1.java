package OA;


public class ascii1 {
    public static int a = 97;
    public static int A = 65;

    public static String decode(String decode) {
        char[] d = decode.toCharArray();
        char[] arr = new char[d.length];
        int tempi = 0;
        for (int i = d.length - 1; i >= 0; i--) {
            arr[tempi++] = d[i];
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder temp;
        for (int i = 0; i < arr.length; ) {
            int count;
            temp = new StringBuilder();
            if (arr[i] == '1') {
                count = 3;
                while (count > 0) {
                    temp.append(arr[i++]);
                    count--;
                }
            } else {
                count = 2;
                while (count > 0) {
                    temp.append(arr[i++]);
                    count--;
                }
            }
            int t = Integer.valueOf(temp.toString());
            if (t <= 90) {
                sb.append((char) ('A' + (t - A)));
            } else {
                sb.append((char) ('a' + (t - a)));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String decode = "2312179862310199501872379231018117927";
        System.out.println(decode(decode));
    }


}
