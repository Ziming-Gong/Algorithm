package Contest.Aug13;

public class LC2375ConstructSmallestNumberFromDIString {
    public static String smallestNumber(String pattern) {
        char[] str = pattern.toCharArray();
        boolean[] status = new boolean[10];
        StringBuilder sb = new StringBuilder();
        if (str[0] == 'I') {
            sb.append(1);
            status[1] = true;
        } else {
            int i = 0;
            while (i + 1 < str.length && str[i + 1] == 'D') {
                i++;
            }
            i += 2;
            sb.append(i);
            status[i] = true;
        }

        for (int i = 0, index = 1; i < str.length; i++, index++) {
            if (str[i] == 'I') {
                if (i + 1 < str.length && str[i + 1] == 'D') {
                    int temp = i;
                    while (temp + 1 < str.length && str[temp + 1] == 'D') {
                        temp++;
                    }
                    int min = findK(status, temp - i + 1);
                    sb.append(min);
                    status[min] = true;
                } else {
                    int min = getMin(status);
                    sb.append(min);
                    status[min] = true;
                }
            } else {
                int temp = i;
                while (temp + 1 < str.length && str[temp + 1] == 'D') {
                    temp++;
                }
                int min = findK(status, temp - i + 1);
                sb.append(min);
                status[min] = true;
            }
        }

        return sb.toString();
    }

    public static int findK(boolean[] status, int k) {
        for (int i = 1; i < status.length; i++) {
            if (!status[i]) {
                k--;
            }
            if (k == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int getMin(boolean[] status) {
        for (int i = 1; i < status.length; i++) {
            if (!status[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String str = "IIIDIDDD";
        System.out.println(smallestNumber(str));
    }

}
