package LeetCodeDays;

public class LC297SerializeandDeserializeBinaryTree {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serPro(root, sb);
        return sb.toString();
    }

    public void serPro(TreeNode cur, StringBuilder sb) {
        if (cur == null) {
            sb.append('#');
            sb.append(',');
            return;
        }
        sb.append(String.valueOf(cur.val));
        sb.append(',');
        serPro(cur.left, sb);
        serPro(cur.right, sb);
    }

    public int index;

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.charAt(0) == '#') {
            return null;
        }
        char[] str = data.toCharArray();
        index = 0;
        StringBuilder sb = new StringBuilder();
        while (str[index] != ',') {
            sb.append(str[index++]);
        }
        TreeNode head = new TreeNode(Integer.valueOf(sb.toString()));
        dePro(str);
        return head;
    }


    public TreeNode dePro(char[] str) {
        if (str[index] == '#') {
            index += 2;
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (str[index] != ',') {
            sb.append(str[index++]);
        }
        index++;
        TreeNode head = new TreeNode(Integer.valueOf(sb.toString()));
        head.left = dePro(str);
        head.right = dePro(str);
        return head;

    }
}
