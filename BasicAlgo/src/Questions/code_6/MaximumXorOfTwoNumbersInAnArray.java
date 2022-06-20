package Questions.code_6;

//https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/solution/
public class MaximumXorOfTwoNumbersInAnArray {

    public int findMaximumXOR(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        NumTier nt = new NumTier();
        nt.add(nums[0]);
        int ans = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i ++){
            ans = Math.max(ans, nt.maxXor(nums[i]));
            nt.add(nums[i]);
        }
        return ans;
    }

    public class NumTier{
        public Node head;
        public NumTier(){
            head = new Node();
        }
        public void add(int value){
            Node cur = head;
            for(int i = 31; i >= 0; i --){
                int path = (value >> i) & 1;
                if(cur.nexts[path] == null){
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int val){
            Node cur = head;
            int ans = 0;
            for(int i = 31; i >= 0; i --){
                int path = (val >> i) & 1;
                int best = i == 31 ? path : path ^ 1;
                best = cur.nexts[best] == null ? best ^ 1 : best;
                ans |= (best ^ path) << i;
                cur = cur.nexts[best];
            }
            return ans;
        }

    }
    public class Node{
        public Node[] nexts = new Node[2];
    }
}
