package basicAlgo.BST;

import sun.tools.jconsole.Plotter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class QueueReconstructionByHeight {
    public static class Unit {
        private int k;
        private int h;

        public Unit(int height, int greater) {
            this.k = greater;
            this.h = height;
        }
    }

    public static class unitComparator implements Comparator<Unit> {
        @Override
        public int compare(Unit u1, Unit u2) {
            return u1.h == u2.h ? u1.k - u2.k : u2.h - u1.h;
        }
    }

    public static class SBTNode {
        public int value;
        public SBTNode l;
        public SBTNode r;
        public int size;

        public SBTNode(int value) {
            this.value = value;
            size = 1;
        }
    }

    public static class SBTree {
        private SBTNode root;

        private SBTNode rightRotate(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            SBTNode left = cur.l;
            cur.l = left.r;
            left.r = cur;
            left.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return left;
        }

        private SBTNode leftRotate(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            SBTNode right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return right;
        }

        private SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = (cur.l != null && cur.l.l != null) ? cur.l.l.size : 0;
            int leftRightSize = (cur.l != null && cur.l.r != null) ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightLeftSize = (cur.r != null && cur.r.l != null) ? cur.r.l.size : 0;
            int rightRightSize = (cur.r != null && cur.r.r != null) ? cur.r.r.size : 0;
            if (leftSize < rightLeftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftSize < rightRightSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightSize < leftLeftSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightSize < leftRightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode insert(SBTNode head, int index, SBTNode cur) {
            if (head == null) {
                return cur;
            }
            head.size++;
            int leftHeadSize = (head.l != null ? head.l.size : 0) + 1;
            if (index < leftHeadSize) {
                head.l = insert(head.l, index, cur);
            } else {
                head.r = insert(head.r, index - leftHeadSize, cur);
            }
            return maintain(head);
        }

        private SBTNode get(SBTNode head, int index) {
            int leftSize = head.l != null ? head.l.size : 0;
            if (index < leftSize) {
                return get(head.l, index);
            } else if (index == leftSize) {
                return head;
            } else {
                return get(head.r, index - leftSize - 1);
            }
        }

        private void process(SBTNode head, LinkedList<Integer> list) {
            if (head == null) {
                return;
            }
            process(head.l, list);
            list.add(head.value);
            process(head.r, list);
        }

        public void insert(int index, int value) {
            SBTNode cur = new SBTNode(value);
            if (root == null) {
                root = cur;
            } else {
                if (index <= root.size) {
                    root = insert(root, index, cur);
                }
            }
        }

        public int get(int index) {
            SBTNode ans = get(root, index);
            return ans.value;
        }

        public LinkedList<Integer> allIndexes() {
            LinkedList<Integer> ans = new LinkedList<>();
            process(root, ans);
            return ans;
        }
    }

    public static int[][] reconstructQueue2(int[][] people) {
        int N = people.length;
        Unit[] units = new Unit[N];
        for (int i = 0; i < N; i++) {
            units[i] = new Unit(people[i][0], people[i][1]);
        }
        Arrays.sort(units, new unitComparator());
        SBTree tree = new SBTree();
        for (int i = 0; i < units.length; i++) {
            tree.insert(units[i].k, i);
        }
        LinkedList<Integer> list = tree.allIndexes();
        int[][] ans = new int[N][2];
        int index = 0;
        for (Integer arr : list) {
            ans[index][0] = units[arr].h;
            ans[index++][1] = units[arr].k;
        }
        return ans;
    }

    public static int[][] reconstructQueue1(int[][] people) {
        int N = people.length;
        Unit[] units = new Unit[N];
        for (int i = 0; i < N; i++) {
            units[i] = new Unit(people[i][0], people[i][1]);
        }
        Arrays.sort(units, new unitComparator());
        ArrayList<Unit> list = new ArrayList<>();
        for (Unit unit : units) {
            list.add(unit.k, unit);
        }
        int[][] ans = new int[N][2];
        int index = 0;
        for(Unit unit : list){
            ans[index][0] = unit.h;
            ans[index++][1] = unit.k ;
        }
        return ans;
    }


    // 通过以下这个测试，
    // 可以很明显的看到LinkedList的插入和get效率不如SBTree
    // LinkedList需要找到index所在的位置之后才能插入或者读取，时间复杂度O(N)
    // SBTree是平衡搜索二叉树，所以插入或者读取时间复杂度都是O(logN)
    public static void main(String[] args) {
        // 功能测试
        int test = 10000;
        int max = 1000000;
        boolean pass = true;
        LinkedList<Integer> list = new LinkedList<>();
        SBTree sbtree = new SBTree();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            list.add(randomIndex, randomValue);
            sbtree.insert(randomIndex, randomValue);
        }
        for (int i = 0; i < test; i++) {
            if (list.get(i) != sbtree.get(i)) {
                pass = false;
                break;
            }
        }
        System.out.println("功能测试是否通过 : " + pass);

        // 性能测试
        test = 50000;
        list = new LinkedList<>();
        sbtree = new SBTree();
        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            list.add(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList插入总时长(毫秒) ： " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            list.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList读取总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            sbtree.insert(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("SBTree插入总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            sbtree.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("SBTree读取总时长(毫秒) :  " + (end - start));

    }
}





























