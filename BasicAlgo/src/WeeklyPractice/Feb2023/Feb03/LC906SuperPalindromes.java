package WeeklyPractice.Feb2023.Feb03;

public class LC906SuperPalindromes {
    public static int superpalindromesInRange(String left, String right) {
        long min = Long.valueOf(left);
        long max = Long.valueOf(right);

        long limit = (long) (Math.sqrt(max));
        int ans = 0;
        long seed = 1;
        long enlarge = 0;
        do {
            enlarge = enlargeEven(seed);
            if (isValid(enlarge, min, max)) {
                ans++;
            }
            enlarge = enlargeOdd(seed);
            if (isValid(enlarge, min, max)) {
                ans++;
            }

            seed++;

        } while (enlarge < limit);
        return ans;
    }


    public static long enlargeOdd(long seed) {
        long ans = seed;
        seed /= 10;
        while (seed > 0) {
            ans = ans * 10 + seed % 10;
            seed /= 10;
        }
        return ans;
    }

    public static long enlargeEven(long seed) {
        long ans = seed;
        while (seed > 0) {
            ans = ans * 10 + seed % 10;
            seed /= 10;
        }
        return ans;
    }

    public static boolean isValid(long num, long min, long max) {
        num *= num;
        if (num >= min && num <= max && isPalindromes(num)) {
            return true;
        }
        return false;
    }

    public static boolean isPalindromes(long num) {
        int offset = 1;
        while (num / 10 > offset) {
            offset *= 10;
        }
        while (num > 0) {
            if (num % 10 != num / offset) {
                return false;
            }
            num %= offset;
            num /= 10;
            offset /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        long num = 2;
        System.out.println(isValid(num, 4, 100));
    }


}









