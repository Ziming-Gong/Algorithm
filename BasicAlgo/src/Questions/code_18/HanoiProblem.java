package Questions.code_18;

public class HanoiProblem {
    // 目标是: 把0~i的圆盘，从from全部挪到to上
    // 返回，根据arr中的状态arr[0..i]，它是最优解的第几步？
    // f(i, 3 , 2, 1) f(i, 1, 3, 2) f(i, 3, 1, 2)

    public static int steps(int[] arr){
        int N = arr.length;
        return process(arr, N -1, 1, 3, 2);
    }

    public static int process(int[] arr, int index, int from, int to, int other){
        if(index < 0){
            return 0;
        }
        if(arr[index] == other){
            return -1;
        }

        if(arr[index] == from){
            return process(arr, index - 1, from, other, to);
        }else{
            int p1 = ((1 << index) - 1);
            int p2 = 1;
            int p3 = process(arr, index - 1, other, to, from);
            if(p3 == -1){
                return -1;
            }
            return p1 + p2 + p3;
        }
    }

    public static int step2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int tmp = 0;
        while (i >= 0) {
            if (arr[i] != from && arr[i] != to) {
                return -1;
            }
            if (arr[i] == to) {
                res += 1 << i;
                tmp = from;
                from = mid;
            } else {
                tmp = to;
                to = mid;
            }
            mid = tmp;
            i--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 3, 2, 1 };
        System.out.println(steps(arr));
        System.out.println(step2(arr));
//        System.out.println(kth(arr));
    }



}
