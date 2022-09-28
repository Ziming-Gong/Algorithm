package Contest.July9;

public class LC2337MovePiecestoObtainaString {
    public boolean canChange(String start, String target) {
        char[] s = start.toCharArray();
        char[] t = target.toCharArray();
        if (s.length != t.length) {
            return false;
        }
        if(!start.replace("_","").equals(target.replace("_", "")) ){
            return false;
        }
        int countLS = 0;
        int countRS = 0;
        int countLT = 0;
        int countRT = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == 'L') {
                countLS++;
            } else if (s[i] == 'R') {
                countRS++;
            }
            if (t[i] == 'L') {
                countLT++;
            } else if (t[i] == 'R') {
                countRT++;
            }
            if (countLT < countLS || countRT > countRS) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(0.0000001D);
    }

}
