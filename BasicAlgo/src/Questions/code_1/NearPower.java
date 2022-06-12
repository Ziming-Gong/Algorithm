package Questions.code_1;

// 已知n是正数
// 返回大于等于，且最接近n的，2的某次方的值
public class NearPower {

    public static int count(int num) {
        num--;
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        return num < 0 ? 1 : num + 1;
    }

    public static void main(String[] args) {
        int n = 255;
        System.out.println(count(n));
    }

}
