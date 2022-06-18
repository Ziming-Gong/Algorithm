package Questions.code_4;

import javax.xml.stream.FactoryConfigurationError;
import java.util.TreeMap;
import java.util.TreeSet;

// 生成长度为size的达标数组
// 达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2
public class MakeNo {
    public static int[] make(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        int halfSize = (size + 1) / 2;
        int[] evenSet = make(halfSize);
        int[] ans = new int[size];
        for (int i = 0; i < halfSize; i++) {
            ans[i] = evenSet[i] * 2;
        }
        for (int i = halfSize, j = 0; i < size; i++, j++) {
            ans[i] = evenSet[j] * 2 + 1;
        }
        return ans;
    }

    // 检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = make(N);
            if (!isValid(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
        System.out.println(isValid(make(1042)));
        System.out.println(isValid(make(2981)));



    }


}
