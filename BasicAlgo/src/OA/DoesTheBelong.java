package OA;

import basicAlgo.Window.SlidingWindowMaxArray;

public class DoesTheBelong {
    public static int solve(int x1, int y1, int x2, int y2, int x3, int y3, int px, int py, int qx, int qy) {
        // 如果a, b, c是不是三角形
        if ((x1 == x2 && x2 == x3) || (y1 == y2 && y2 == y3)) {
            return 0;
        }
        // p在不在里面
        boolean ansP = true;
        ansP &= include(x1, y1, px, py, x2, y2, x3, y3);
        ansP &= include(x2, y2, px, py, x1, y1, x3, y3);
        ansP &= include(x3, y3, px, py, x2, y2, x1, y1);
        // q在不在里面
        boolean ansQ = true;
        ansQ &= include(x1, y1, qx, qy, x2, y2, x3, y3);
        ansQ &= include(x2, y2, qx, qy, x1, y1, x3, y3);
        ansQ &= include(x3, y3, qx, qy, x2, y2, x1, y1);

        if (ansP && ansQ) {
            return 3;
        } else if (ansP) {
            return 1;
        } else if (ansQ) {
            return 2;
        } else {
            return 4;
        }
    }

    public static boolean include(int x, int y, int tx, int ty, int ax, int ay, int bx, int by) {
        if ((tx == x && ty == y)) {
            return true;
        }
        float edge1 = ((float) (bx - x)) / ((float) (by - y));
        float edge2 = ((float) (ax - x)) / ((float) (ay - y));
        float target = (((float) (tx - x)) / ((float) (ty - y)));
        return target <= Math.max(edge1, edge2) && target >= Math.min(edge1, edge2);
    }

    public static void main(String[] args) {
        int x1 = 3;
        int y1 = 1;
        int x2 = 7;
        int y2 = 1;
        int x3 = 5;
        int y3 = 5;
        int px = 1;
        int py = 1;
        int qx = 2;
        int qy = 2;
        System.out.println(solve(x1, y1, x2, y2, x3, y3, px, py, qx, qy));
    }
}
