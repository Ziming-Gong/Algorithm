package basicAlgo.IndexTree;

public class IndexTree_Code {
    public static class IndexTree {
        private int[] tree;
        private int N;

        public IndexTree(int N) {
            this.N = N;
            tree = new int[N + 1];
        }

        public IndexTree(int[] arr) {
            N = arr.length;
            tree = new int[N + 1];
            for (int i = 0; i < arr.length; i++) {
                int index = i + 1;
                while (index <= N) {
                    tree[index] += arr[i];
                    index += index & -index;
                }

            }
        }

        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & (~index + 1);
            }
            return res;
        }

        public void add(int index, int add) {
            while (index <= N) {
                tree[index] += add;
                index += index & (~index + 1);
            }
        }

        public void print() {
            for (int i = 0; i < tree.length; i++) {
                System.out.printf(tree[i] + ", ");
            }
        }
    }

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public Right(int[] arr) {
            N = arr.length;
            nums = new int[N + 1];
            for (int i = 0; i < N; i++) {
                nums[i + 1] = arr[i];
            }
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static int[] generateArray(int N, int V) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * V);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");

        System.out.println("general array test");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateArray(N, V);
            IndexTree indexTree = new IndexTree(arr);
            Right right = new Right(arr);
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                indexTree.add(index, add);
                right.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test end");
    }
}
