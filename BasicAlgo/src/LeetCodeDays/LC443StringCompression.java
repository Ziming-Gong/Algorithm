package LeetCodeDays;

public class LC443StringCompression {
    public int compress(char[] chars) {
        char cur;
        int cnt;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            cur = chars[i];
            cnt = 0;
            while (chars[i] == cnt) {
                i++;
                cnt++;
            }
            chars[index ++] = cur;
            if(cnt == 1){
                continue;
            }
            index = put(chars, index, cnt);
        }
        return index;
    }

    public int put(char[] res, int index, int num) {
        int offset = 1;
        while (num / 10 >= offset) {
            offset *= 10;
        }

        while (offset > 0) {
            int cur = (num / offset);
            char c = (char) (cur + '0');
//            System.out.println(c);
            res[index++] = c;
            num %= offset;
            offset /= 10;
        }
        return index;
    }

    public int get(int num) {
        int ans = 0;
        while (num > 0) {
            num /= 10;
            ans++;
        }
        return ans;
    }
}
