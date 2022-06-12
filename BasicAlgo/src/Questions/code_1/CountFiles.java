package Questions.code_1;

import java.io.File;
import java.util.LinkedList;
import java.util.Stack;


public class CountFiles {
    public static int count(String folderPath) {
        File root = new File(folderPath);
        if (!root.isFile() && !root.isDirectory()) {
            return 0;
        }
        int count = 0;
        LinkedList<File> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            File file = queue.pollFirst();
            for (File cur : file.listFiles()) {
                if (cur.isDirectory()) {
                    queue.addLast(cur);
                }
                if (cur.isFile()) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getFileNumber(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files = 0;
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File next : folder.listFiles()) {
                if (next.isFile()) {
                    files++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        System.out.println(count("/Users/ming/desktop"));
    }

}
