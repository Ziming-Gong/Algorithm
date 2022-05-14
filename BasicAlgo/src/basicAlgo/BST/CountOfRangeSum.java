package basicAlgo.BST;

import java.util.HashSet;

public class CountOfRangeSum {


    public static class SBTNode {
        private long key;
        private SBTNode l;
        private SBTNode r;
        private int size;
        private long all;

        public SBTNode(long key) {
            this.key = key;
            l = null;
            r = null;
            size = 1;
            all = 1;
        }
    }

    public static class SizeBalancedTreeSet {
        private SBTNode root;
        private HashSet<Long> set;

        public SizeBalancedTreeSet() {
            root = null;
            set = new HashSet<>();
        }

        private SBTNode rightRotate(SBTNode cur) {
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTNode left = cur.l;
            cur.l = left.l;
            left.l = cur;
            left.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            left.all = cur.all;
            cur.all = same + (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0);
            return left;
        }

        private SBTNode leftRotate(SBTNode cur) {
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);

            SBTNode right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            right.all = cur.all;
            cur.all = same + (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0);
            return right;
        }

        public SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            SBTNode leftNode = cur.l;
            SBTNode rightNode = cur.r;
            int leftSize = leftNode != null ? leftNode.size : 0;
            int rightSize = rightNode != null ? rightNode.size : 0;
            int leftLeftSize = (cur.l != null && cur.l.l != null) ? cur.l.l.size : 0;
            int leftRightSize = (cur.l != null && cur.l.r != null) ? cur.l.r.size : 0;
            int rightLeftSize = (cur.r != null && cur.r.l != null) ? cur.r.l.size : 0;
            int rightRightSize = (cur.r != null && cur.r.r != null) ? cur.r.r.size : 0;
            if (leftSize < rightRightSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (leftSize < rightLeftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.r = maintain(cur.r);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightSize < leftLeftSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur.l = maintain(cur.l);
            } else if (rightSize < leftRightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }






        private SBTNode add(SBTNode cur, long key, boolean contains) {
            if (cur == null) {
                return new SBTNode(key);
            } else {
                cur.all++;
                if (key == cur.key) {
                    return cur;
                } else {
                    if (!contains) {
                        cur.size++;
                    }
                    if (cur.key > key) {
                        cur.l = add(cur.l, key, contains);
                    } else {
                        cur.r = add(cur.r, key, contains);
                    }
                    return maintain(cur);
                }
            }
        }



        public void add(long key) {
            boolean contain = set.contains(key);
            root = add(root, key, contain);
            set.add(key);

        }


        public long lessKeySize(long key) {
            SBTNode cur = root;
            long ans = 0;
            while (cur != null) {
                if (cur.key == key) {
                    return ans + (cur.l != null ? cur.l.all : 0);
                } else if (cur.key > key) {
                    cur = cur.l;
                } else {
                    ans += cur.all - (cur.r != null ? cur.r.all : 0);
                    cur = cur.r;
                }
            }
            return ans;
        }
    }

    public static int countRangeSum2(int[] nums, int lower, int upper) {
        if (lower > upper || nums == null || nums.length < 0) {
            return 0;
        }
        SizeBalancedTreeSet tree = new SizeBalancedTreeSet();
        tree.add(0);
        int ans = 0;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            //add sum[i]
            sum += nums[i];

            long small = sum - upper;
            long big = sum - lower;

            long a = tree.lessKeySize(small);
            long b = tree.lessKeySize(big + 1);
            ans += b - a;
            tree.add(sum);
        }
        return ans;
    }

    public static int countRangeSum1(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private static int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1)
            return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper)
                + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower)
                k++;
            while (j < end && sums[j] - sums[i] <= upper)
                j++;
            while (t < end && sums[t] < sums[i])
                cache[r++] = sums[t++];
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] generateArray(int len, int varible) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * varible);
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 200;
        int varible = 50;
        for (int i = 0; i < 10000; i++) {
            int[] test = generateArray(len, varible);
            int lower = (int) (Math.random() * varible) - (int) (Math.random() * varible);
            int upper = lower + (int) (Math.random() * varible);
            int ans1 = countRangeSum1(test, lower, upper);
            int ans2 = countRangeSum2(test, lower, upper);
            if (ans1 != ans2) {
                printArray(test);
                System.out.println(lower);
                System.out.println(upper);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }

    }


}



















