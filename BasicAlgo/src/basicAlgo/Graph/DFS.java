package basicAlgo.Graph;

import java.util.HashSet;
import java.util.Stack;

public class DFS {

    public static void DFS(Node start){
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        System.out.println(start.val);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            for(Node next : node.nexts){
                if(!set.contains(next)){
                    stack.add(node);
                    set.add(next);
                    stack.add(next);
                    System.out.println(next.val);
                    break;
                }
            }

        }
    }
}
