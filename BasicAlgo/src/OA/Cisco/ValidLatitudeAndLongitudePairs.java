package OA.Cisco;

import java.io.*;

public class ValidLatitudeAndLongitudePairs {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;

            for (int i = 0; i < n; i++) {
                in.nextToken();
                String string = in.toString();
                System.out.println(isVal(string));
            }
        }

    }

    public static boolean isVal(String s) {
        String[] strings = s.split(",");
        String X = strings[0].substring(1, strings[0].length() - 1);
        String Y = strings[1].substring(0, strings[2].length() - 1);
        double x = Double.valueOf(X);
        double y = Double.valueOf(Y);
        if (x < -90 || x > 90 || y < -180 || y > 180) {
            return false;
        }
        return true;

    }
}
