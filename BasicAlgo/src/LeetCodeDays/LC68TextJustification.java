package LeetCodeDays;

import java.util.ArrayList;
import java.util.List;

public class LC68TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int L = 0, R = 0;
        int cur = 0, num = 0;
        while (R < words.length) {
            cur = words[R].length();
            int wordLen = words[R++].length();
            while (R < words.length && cur + words[R].length() + 1 <= maxWidth) {
                cur += words[R].length() + 1;
                wordLen += words[R++].length();
            }
            if (R - L == 1) {
                StringBuilder sb = new StringBuilder();
                sb.append(words[L++]);
                for (int i = sb.length(); i < maxWidth; i++)
                    sb.append(" ");
                ans.add(sb.toString());
                continue;
            }
            if(R == words.length){
                StringBuilder sb = new StringBuilder();
                for(int i = L; i < R; i ++){
                    sb.append(words[i] + " ");
                }
                for (int i = sb.length(); i < maxWidth; i++)
                    sb.append(" ");
                ans.add(sb.toString());
                continue;

            }

            int gap = (maxWidth - wordLen) / (R - L - 1);
            int frontNum = (maxWidth - wordLen) % (R - L - 1);
            if (R < words.length && cur + words[R].length() <= maxWidth) {
                R++;
                gap = 1;
                frontNum = 0;
            }
            StringBuilder sb = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            for (int i = 1; i <= gap; i++) {
                temp.append(" ");
            }
            for (int i = L; i < R - 1; i++) {
                if (frontNum > 0) {
                    sb.append(words[i] + temp + " ");
                    frontNum--;
                } else {
                    sb.append(words[i] + temp);
                }
            }
            sb.append(words[R - 1]);
            ans.add(sb.toString());
            L = R;
        }
        return ans;
    }


}
