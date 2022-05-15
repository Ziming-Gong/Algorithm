package basicAlgo.BST;

//https://leetcode.com/problems/sliding-window-median/submissions/
public class SlidingWindowMedian {
    public static class SBTNode<K extends Comparable<K>>{
        private K key;
        private SBTNode<K> l;
        private SBTNode<K> r;
        private int size;

        public SBTNode(K key){
            this.key = key;
            size = 1;
        }
    }
    public static class SizeBalancedTreeMap<K extends Comparable<K>>{
        private SBTNode<K> root;

        private SBTNode<K> rightRotate(SBTNode<K> cur){
            if(cur == null){
                return null;
            }
            SBTNode<K> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            left.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return left;
        }

        private SBTNode<K> leftRotate(SBTNode<K> cur){
            if(cur == null){
                return null;
            }
            SBTNode<K> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return right;
        }

        private SBTNode<K> maintain(SBTNode<K> cur){
            int leftSize = cur.l != null ? cur.l.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int leftLeftSize = (cur.l != null && cur.l.l != null) ? cur.l.l.size : 0;
            int leftRightSize = (cur.l != null && cur.l.r != null) ? cur.l.r.size : 0;
            int rightLeftSize = (cur.r != null && cur.r.l != null) ? cur.r.l.size : 0;
            int rightRightSize = (cur.r != null && cur.r.r != null) ? cur.r.r.size : 0;

            if(leftSize < rightLeftSize){
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if ( leftSize < rightRightSize){
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }else if ( rightSize < leftLeftSize){
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode<K> findLastIndex(K key){
            SBTNode<K> cur = root;
            SBTNode<K> ans = root;
            while(cur != null){
                ans = cur;
                if(key.compareTo(cur.key) == 0){
                    break;
                }else if(key.compareTo(cur.key) < 0){
                    cur = cur.l;
                }else{
                    cur = cur.r;
                }
            }
            return ans;
        }

        private SBTNode<K> add(SBTNode<K> cur, K key){
            if(cur == null){
                return new SBTNode(key);
            }else{
                cur.size ++;
                if(key.compareTo(cur.key) < 0){
                    cur.l = add(cur.l, key);
                }else{
                    cur.r = add(cur.r, key);
                }
                return maintain(cur);
            }
        }

        private SBTNode<K> delete(SBTNode<K> cur, K key){
            cur.size --;
            if(key.compareTo(cur.key) == 0){
                if(cur.l == null && cur.r == null){
                    cur = null;
                }else if( cur.l == null && cur.r != null){
                    cur = cur.r;
                }else if (cur.l != null && cur.r == null){
                    cur = cur.l;
                }else{
                    SBTNode<K> right = cur.r;
                    SBTNode<K> pre = null;
                    right.size --;
                    while(right.l != null){
                        pre = right;
                        right = right.l;
                        right.size --;
                    }
                    if(pre != null){
                        pre.l = right.r;
                        right.r = cur.r;
                    }
                    right.l = cur.l;
                    // right.size = cur.size;
                    right.size = right.l.size + (right.r == null ? 0 : right.r.size) + 1;
                    cur = right;
                }
            }else if (key.compareTo(cur.key) < 0){
                cur.l = delete(cur.l, key);
            }else{
                cur.r = delete(cur.r, key);
            }

            return cur;
        }



        private SBTNode<K> getIndex(SBTNode<K> cur, int kth){
            if(kth == (cur.l != null ? cur.l.size : 0) + 1){
                return cur;
            }else if(kth <= (cur.l != null ? cur.l.size : 0) ){
                return getIndex(cur.l, kth);
            }else{
                return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
            }
        }

        public void add(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K> lastNode = findLastIndex(key);
            if (lastNode == null || key.compareTo(lastNode.key) != 0) {
                root = add(root, key);
            }
        }

        private boolean containsKey(K key){
            if(key == null){
                throw new RuntimeException("invalid params");
            }
            SBTNode<K> last = findLastIndex(key);
            return last != null && key.compareTo(last.key) == 0 ? true : false;
        }
        public void remove(K key){
            if(key == null){
                throw new RuntimeException("invalid params");
            }
            if(containsKey(key)){
                root = delete(root, key);
            }
        }

        private int size(){
            return root != null? root.size : 0;
        }

        public K getIndexKey(int index){
            if(index < 0 || index >= this.size()){
                throw new RuntimeException("invalid params");
            }
            return getIndex(root, index + 1).key;
        }


    }

    public static class Node implements Comparable<Node>{
        public int index;
        public int value;

        public Node (int index , int value){
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o){
            return value != o.value ? Integer.valueOf(value).compareTo(o.value) : Integer.valueOf(index).compareTo(o.index);
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k > nums.length || k <= 0){
            return null;
        }
        SizeBalancedTreeMap<Node> tree = new SizeBalancedTreeMap<>();
        int index = 0;
        double[] ans = new double[nums.length - k + 1];
        for(; index < k - 1; index ++){
            tree.add(new Node(index, nums[index]));
        }
        int i = 0;
        for(; index < nums.length; index ++){
            tree.add(new Node(index, nums[index]));
            if(tree.size() % 2 == 0){
                Node up = tree.getIndexKey(tree.size()/2 -1 );
                Node down = tree.getIndexKey(tree.size()/2);
                ans[i++] = ((double) up.value + (double)down.value) /2 ;
            }else{
                Node mid = tree.getIndexKey(tree.size()/2);
                ans[i ++] = (double)mid.value;
            }
            tree.remove(new Node( index - k + 1, nums[index - k + 1]));
        }
        return ans;
    }
}
