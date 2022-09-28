package Contest.Sep3;

import java.util.HashSet;

public class LC6167CheckDistancesBetweenSameLetters {
    public boolean checkDistances(String s, int[] distance) {
        char[] str = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < str.length; i++) {
            if (set.contains(str[i])) {
                continue;
            }
            int j = i + 1;
            while (str[j] != str[i]) {
                j++;
            }
            if (j - i - 1 != distance[str[i] - 'a']) {
                return false;
            }
            set.add(str[i]);
        }
        return true;

    }
}
