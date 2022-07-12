package basicAlgo.BST;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.w3c.dom.ls.LSException;

public class SizeBalanceTreeCode {
    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public SBTNode<K, V> left;
        public SBTNode<K, V> right;
        public int size;

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
        public SBTNode<K, V> root;

        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {

            SBTNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            left.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
//            left.size = (left.left != null ? left.left.size : 0) + (left.right != null ? left.right.size : 0) + 1;
            return left;
        }

        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            right.size = cur.size;
            cur.size = (cur.right != null ? cur.right.size : 0) + (cur.left != null ? cur.left.size : 0) + 1;
            return right;
        }

        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            SBTNode<K, V> leftNode = cur.left;
            SBTNode<K, V> rightNode = cur.right;
            int leftSize = leftNode != null ? leftNode.size : 0;
            int rightSize = rightNode != null ? rightNode.size : 0;
            int leftLeftSize = leftNode != null && leftNode.left != null ? leftNode.left.size : 0;
            int leftRightSize = leftNode != null && leftNode.right != null ? leftNode.right.size : 0;
            int rightLeftSize = rightNode != null && rightNode.left != null ? rightNode.left.size : 0;
            int rightRightSize = rightNode != null && rightNode.right != null ? rightNode.right.size : 0;
            if (leftSize < rightLeftSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.right = maintain(cur.right);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            } else if (leftSize < rightRightSize) {
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            } else if (rightSize < leftLeftSize) {
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (rightSize < leftRightSize) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode<K, V> findLastIndex(K key) {
            SBTNode<K, V> cur = root;
            SBTNode<K, V> prev = root;
            while (cur != null) {
                prev = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) > 0) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return prev;
        }

        private SBTNode<K, V> findLastNoSmallIndex(K key) {
            SBTNode<K, V> cur = root;
            SBTNode<K, V> pre = null;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    pre = root;
                    break;
                } else if (key.compareTo(cur.key) > 0) {
                    cur = cur.right;
                } else if (key.compareTo(cur.key) < 0) {
                    pre = cur;
                    cur = cur.left;
                }
            }
            return pre;
        }

        private SBTNode<K, V> findLastNoBigIndex(K key) {
            SBTNode<K, V> pre = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    pre = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    pre = cur;
                    cur = cur.right;
                }
            }
            return pre;
        }

        //must not duplicate because size ++
        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SBTNode<>(key, value);
            } else {
                cur.size++;
                if (key.compareTo(cur.key) < 0) {
                    cur.left = add(cur.left, key, value);
                } else {
                    cur.right = add(cur.right, key, value);
                }
                return maintain(cur);
            }
        }

        // key must in this tree!!
        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            } else if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            } else if (key.compareTo(cur.key) == 0) {
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else if (cur.right == null && cur.left != null) {
                    cur = cur.left;
                } else {
                    SBTNode<K, V> des = cur.right;
                    SBTNode<K, V> pre = null;
                    des.size--;
                    while (des.left != null) {
                        pre = des;
                        des.size--;
                        des = des.left;
                    }
                    if (pre != null) {
                        pre.left = des.right;
                        des.right = cur.right;
                    }
                    des.left = cur.left;
                    des.size = (des.left != null ? des.left.size : 0) + (des.right != null ? des.right.size : 0) + 1;
                    cur = des;
                }
            }
            return cur;
        }

        private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
            int leftSize = cur.left != null ? cur.left.size : 0;
            if (leftSize + 1 == kth) {
                return cur;
            } else if (kth <= leftSize) {
                return getIndex(cur.left, kth);
            } else {
                return getIndex(cur.right, kth - leftSize - 1);
            }
        }

        public int size() {
            return root != null ? root.size : 0;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> last = findLastIndex(key);
            return last != null && key.compareTo(last.key) == 0 ? true : false;
        }

        public void put(K key, V value) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                lastNode.value = value;
            } else {
                root = add(root, key, value);
            }
        }

        public void remove(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            if (containsKey(key)) {
                root = delete(root, key);
            }
        }

        public K getIndexKey(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter.");
            }
            return getIndex(root, index + 1).key;
        }

        public V getIndexValue(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter.");
            }
            return getIndex(root, index + 1).value;
        }

        public V get(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                return lastNode.value;
            } else {
                return null;
            }
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.key;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.key;
        }

        public K floorKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNoBig = findLastNoBigIndex(key);
            return lastNoBig != null ? lastNoBig.key : null;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNoSmail = findLastNoSmallIndex(key);
            return lastNoSmail != null ? lastNoSmail.key : null;
        }
    }

    // for test
    public static void printAll(SBTNode<String, Integer> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    // for test
    public static void printInOrder(SBTNode<String, Integer> head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + "(" + head.key + "," + head.value + ")" + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    // for test
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        SizeBalancedTreeMap<String, Integer> sbt = new SizeBalancedTreeMap<String, Integer>();
        sbt.put("d", 4);
        sbt.put("c", 3);
        sbt.put("OA.MaxNumDinstinctNum.a", 1);
        sbt.put("b", 2);
        // sbt.put("e", 5);
        sbt.put("g", 7);
        sbt.put("f", 6);
        sbt.put("h", 8);
        sbt.put("i", 9);
        sbt.put("OA.MaxNumDinstinctNum.a", 111);
        System.out.println(sbt.get("OA.MaxNumDinstinctNum.a"));
        sbt.put("OA.MaxNumDinstinctNum.a", 1);
        System.out.println(sbt.get("OA.MaxNumDinstinctNum.a"));
        for (int i = 0; i < sbt.size(); i++) {
            System.out.println(sbt.getIndexKey(i) + " , " + sbt.getIndexValue(i));
        }
        printAll(sbt.root);
        System.out.println(sbt.firstKey());
        System.out.println(sbt.lastKey());
        System.out.println(sbt.floorKey("g"));
        System.out.println(sbt.ceilingKey("g"));
        System.out.println(sbt.floorKey("e"));
        System.out.println(sbt.ceilingKey("e"));
        System.out.println(sbt.floorKey(""));
        System.out.println(sbt.ceilingKey(""));
        System.out.println(sbt.floorKey("j"));
        System.out.println(sbt.ceilingKey("j"));
        sbt.remove("d");
        printAll(sbt.root);
        sbt.remove("f");
        printAll(sbt.root);

    }

}

























