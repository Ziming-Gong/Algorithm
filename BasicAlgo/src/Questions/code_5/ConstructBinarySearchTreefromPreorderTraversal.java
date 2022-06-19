package Questions.code_5;

import javax.swing.tree.TreeNode;
import java.util.Stack;

public class ConstructBinarySearchTreefromPreorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, 0, preorder.length - 1);
    }

    public TreeNode build(int[] arr, int L, int R) {
        if (L > R) {
            return null;
        }
        int firstBig = L;
        for (; firstBig <= R; firstBig++) {
            if (arr[firstBig] > arr[L])
                break;
        }
        TreeNode head = new TreeNode(arr[L]);
        head.left = build(arr, L + 1, firstBig - 1);
        head.right = build(arr, firstBig, R);
        return head;
    }

    /**
     * 单调栈优化
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder1(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int[] bigger = new int[preorder.length];
        for(int i = 0; i < preorder.length; i ++){
            while(!stack.isEmpty() && preorder[stack.peek()] < preorder[i]){
                bigger[stack.pop()] = i;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            bigger[stack.pop()] = -1;
        }
        return build(preorder, 0, preorder.length - 1, bigger);
    }

    public TreeNode build(int[] arr, int L, int R, int[] bigger){
        if(L > R){
            return null;
        }
        int firstBig = bigger[L] == -1 ? R + 1 : bigger[L];
        TreeNode head = new TreeNode(arr[L]);
        head.left = build(arr, L+1, firstBig - 1, bigger);
        head.right = build(arr, firstBig, R, bigger);
        return head;
    }
    /**
     *
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        int[] stack = new int[preorder.length];
        int size = 0;
        int[] bigger = new int[preorder.length];
        for(int i = 0; i < preorder.length; i ++){
            while(size != 0 && preorder[stack[size - 1]] < preorder[i]){
                bigger[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while(size != 0){
            bigger[stack[--size]] = -1;
        }
        return build2(preorder, 0, preorder.length - 1, bigger);
    }

    public TreeNode build2(int[] arr, int L, int R, int[] bigger){
        if(L > R){
            return null;
        }
        int firstBig = bigger[L] == -1 ? R + 1 : bigger[L];
        TreeNode head = new TreeNode(arr[L]);
        head.left = build2(arr, L+1, firstBig - 1, bigger);
        head.right = build2(arr, firstBig, R, bigger);
        return head;
    }
}
