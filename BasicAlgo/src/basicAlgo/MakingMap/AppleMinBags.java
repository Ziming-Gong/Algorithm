package basicAlgo.MakingMap;

public class AppleMinBags {
    public static void main(String[] args) {
        for (int i = 0; i < 60; i++) {
            System.out.println(i + " bag : " + min2(i));
        }
    }


    public static int min1(int apple) {
        int bag8 = apple / 8;
        for (int i = bag8; i >= 0; i--) {
            if ((apple - i * 8) % 6 == 0) {
                int bag6 = (apple - i * 8) / 6;
                return bag6 + i;
            }
        }
        return -1;
    }

    public static int min2(int apple) {
        if ((apple & 1) != 0) {
            return -1;
        }
        if (apple >= 18) {
            return (apple - 18) / 8 + 3;
        } else {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
    }
}
