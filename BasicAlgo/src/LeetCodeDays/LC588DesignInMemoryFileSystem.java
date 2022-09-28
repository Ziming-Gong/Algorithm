package LeetCodeDays;

import basicAlgo.Greedy.BestArrange;
import basicAlgo.mergesorted.ans;
import com.sun.tools.javac.code.TargetType;

import javax.naming.directory.DirContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class LC588DesignInMemoryFileSystem {
    public static class FileSystem {

        Directory desk;

        public FileSystem() {
            desk = new Directory("desk");
        }

        public List<String> ls(String path) {
            if (path.length() == 1) {
                PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> a.compareTo(b));
                Directory cur = desk;
                List<String> ans = new ArrayList<>();
                for (int i = 0; i < cur.files.size(); i++) {
                    queue.add(cur.files.get(i));
                }
                while (!queue.isEmpty()) {
                    ans.add(queue.poll());
                }
                return ans;
            }

            PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> a.compareTo(b));
            Directory cur = desk;
            List<String> ans = new ArrayList<>();
            char[] str = path.toCharArray();

            int right = str.length - 1;
            StringBuilder stringBuilder = new StringBuilder();
            while (str[right] != '/') {
                stringBuilder.append(str[right--]);
            }

            String target = stringBuilder.reverse().toString();


            for (int i = 1; i < right; i++) {
                if (str[i] == '/') {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                while (i < right && str[i] != '/') {
                    sb.append(str[i++]);
                }
                String next = sb.toString();
                if (!cur.sonDirectoryMap.containsKey(next)) {
                    return ans;
                }
                cur = cur.sonDirectoryMap.get(next);
            }
            if (cur.fileMap.containsKey(target)) {
                ans.add(cur.fileMap.get(target).fileName);
                return ans;
            }
            if (!cur.sonDirectoryMap.containsKey(target)) {
                return ans;
            }
            cur = cur.sonDirectoryMap.get(target);
            for (int i = 0; i < cur.files.size(); i++) {
                queue.add(cur.files.get(i));
            }
            while (!queue.isEmpty()) {
                ans.add(queue.poll());
            }
            return ans;
        }

        public void mkdir(String path) {
            Directory cur = desk;
            char[] str = path.toCharArray();
            for (int i = 1; i < str.length; i++) {
                StringBuilder sb = new StringBuilder();
                while (i < str.length && str[i] != '/') {
                    sb.append(str[i++]);
                }
                String next = sb.toString();
                if (!cur.sonDirectoryMap.containsKey(next)) {
                    Directory nextD = new Directory();
                    cur.sonDirectoryMap.put(next, nextD);
                    cur.files.add(next);
                    cur = nextD;
                } else {
                    cur = cur.sonDirectoryMap.get(next);
                }

            }

        }

        public void addContentToFile(String filePath, String content) {
            char[] path = filePath.toCharArray();
            int right = path.length - 1;
            StringBuilder stringBuilder = new StringBuilder();
            while (path[right] != '/') {
                stringBuilder.append(path[right--]);
            }
            String fn = stringBuilder.reverse().toString();
            File newFile = new File(content, fn);
            Directory cur = desk;
            for (int i = 1; i < right; i++) {
                StringBuilder sb = new StringBuilder();
                while (i < right && path[i] != '/') {
                    sb.append(path[i++]);
                }
                String next = sb.toString();
                if (!cur.sonDirectoryMap.containsKey(next)) {
                    Directory nextD = new Directory();
                    cur.sonDirectoryMap.put(next, nextD);
                    cur.files.add(next);
                    cur = nextD;
                } else {
                    cur = cur.sonDirectoryMap.get(next);
                }
            }
            if (cur.fileMap.containsKey(fn)) {
                File file = cur.fileMap.get(fn);
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(file.content);
                stringBuilder1.append(content);
                file.content = stringBuilder1.toString();
                return;

            }
            cur.fileMap.put(fn, newFile);
            cur.files.add(fn);

        }

        public String readContentFromFile(String filePath) {
            char[] path = filePath.toCharArray();
            int right = path.length - 1;
            StringBuilder stringBuilder = new StringBuilder();
            while (path[right] != '/') {
                stringBuilder.append(path[right--]);
            }
            String fn = stringBuilder.reverse().toString();
            Directory cur = desk;
            for (int i = 1; i < right; i++) {
                StringBuilder sb = new StringBuilder();
                while (i < right && path[i] != '/') {
                    sb.append(path[i++]);
                }
                String next = sb.toString();
                if (!cur.sonDirectoryMap.containsKey(next)) {
                    return "";
                } else {
                    cur = cur.sonDirectoryMap.get(next);
                }
            }
            if (!cur.fileMap.containsKey(fn)) {
                return "";
            }
            File target = cur.fileMap.get(fn);
            return target.content;

        }

        public class File {
            public String fileName;
            public String content;

            public File(String c, String fn) {
                content = c;
                fileName = fn;
            }
        }

        public class Directory {
            public String name;
            public HashMap<String, Directory> sonDirectoryMap;
            public HashMap<String, File> fileMap;
            public ArrayList<String> files;

            public Directory(String n) {
                name = n;
                sonDirectoryMap = new HashMap<>();
                files = new ArrayList<>();
                fileMap = new HashMap<>();
            }

            public Directory() {
                sonDirectoryMap = new HashMap<>();
                files = new ArrayList<>();
                fileMap = new HashMap<>();
            }
        }
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello world");
        fs.addContentToFile("/a/b/c/d", " hello hello world");
        fs.addContentToFile("/a/b/c/dddd", "winstons");
        fs.ls("/a/b/c");
    }
}
