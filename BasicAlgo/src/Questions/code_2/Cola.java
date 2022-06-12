package Questions.code_2;

/*
 * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
 * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
 * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
 * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
 * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
 * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
 * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
 * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
 * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
 */
public class Cola {


    /**
     * @param m 可乐数量
     * @param a 100元数量
     * @param b 20元数量
     * @param c 10元数量
     * @param x 可乐价格
     * @return
     */
    public static int putTimes(int m, int a, int b, int c, int x) {
        if (m == 0) {
            return 0;
        }
        int restMoney = 0;
        int restSheets = 0;
        int ans = 0;
        int[] moneys = {100, 50, 10};
        int[] sheets = {c, b, a};
        for (int i = 0; i < 3; i++) {
            //上次剩余的钱一起算第一次
            int first = (x - restMoney + moneys[i] - 1) / moneys[i];//买第一次需要多少张
            if (first <= sheets[i]) {
                sheets[i] -= first;
                ans += restSheets + first;
                getRest(moneys, sheets, i + 1, (first * moneys[i]) - (x - restMoney), 1);
                m--;
                if (m == 0) {
                    break;
                }
            } else {
                restMoney += sheets[i] * moneys[i];
                restSheets += sheets[i];
                continue;
            }
            int oneTimeSheets = (x + moneys[i] - 1) / moneys[i];//一次需要多少张
            int totalItems = Math.min(m, sheets[i] / oneTimeSheets); // 能解决多少个
            sheets[i] -= totalItems * oneTimeSheets; //
            m -= totalItems;
            ans += totalItems * oneTimeSheets;
            getRest(moneys, sheets, i + 1, (oneTimeSheets * moneys[i]) - x, totalItems);
            if (m == 0) {
                break;
            }
            restMoney = sheets[i] * moneys[i];
            restSheets = sheets[i];
        }
        return m == 0 ? ans : -1;
    }

    public static void getRest(int[] moneys, int[] sheets, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            sheets[i] += oneTimeRest / moneys[i] * times;
            oneTimeRest %= moneys[i];
        }
    }

    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            getRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }


    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }

}
