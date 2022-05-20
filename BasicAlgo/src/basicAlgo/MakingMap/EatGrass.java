package basicAlgo.MakingMap;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import javax.xml.bind.SchemaOutputResolver;

public class EatGrass {
    public static String WhoWin(int N) {
        if (N < 5) {
            return N == 0 || N == 2 ? "后手" : "先手";
        }
        int ans = 1;
        for (int i = 1; i <= N; i *= 4) {
            if (WhoWin(N - i).equals("后手")) {
                return "先手";
            }
        }
        return "后手";
    }

    public static String handSome(int N) {
        return N%5 == 0 ||N % 5 == 2 ? "后手":"先手";
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println(handSome(i));
        }
    }
}
