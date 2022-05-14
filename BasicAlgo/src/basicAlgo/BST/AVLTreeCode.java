package basicAlgo.BST;

public class AVLTreeCode {
    public static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public AVLNode<K, V> left;
        public AVLNode<K, V> right;
        public int height;

        public AVLNode(K key, V value) {
            k = key;
            v = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    public static class AVLTree<K extends Comparable<K>, V> {
        private AVLNode<K, V> root;
        private int size;

        public AVLTree() {
            root = null;
            size = 0;
        }

        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0)) + 1;
            right.height = Math.max((right.left != null ? right.left.height : 0), (right.right != null ? right.right.height : 0)) + 1;
            return right;
        }

        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0)) + 1;
            left.height = Math.max((left.left != null ? left.left.height : 0), (left.right != null ? left.right.height : 0)) + 1;
            return left;
        }

        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int lHeight = cur.left != null ? cur.left.height : 0;
            int rHeight = cur.right != null ? cur.right.height : 0;
            if (Math.abs(lHeight - rHeight) > 1) {
                if (lHeight > rHeight) {
                    int l = cur.left.left != null ? cur.left.left.height : 0;
                    int r = cur.right.right != null ? cur.right.right.height : 0;
                    if (l >= r) {
                        cur = rightRotate(cur);
                    } else {
                        cur.left = leftRotate(cur.left);
                        cur = rightRotate(cur);
                    }
                } else {
                    int l = cur.right.left != null ? cur.right.left.height : 0;
                    int r = cur.right.right != null ? cur.right.right.height : 0;
                    if (r >= l) {
                        cur = leftRotate(cur);
                    } else {
                        cur.right = rightRotate(cur.right);
                        cur = leftRotate(cur);
                    }

                }
            }
            return cur;
        }

        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<>(key, value);
            } else {
                if (key.compareTo(cur.k) > 0) {
                    cur.right = add(cur.right, key, value);
                } else {
                    cur.right = add(cur.left, key, value);
                }
                cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0)) + 1;
                return maintain(cur);
            }
        }

        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            if (key.compareTo(cur.k) < 0) {
                cur.left = delete(cur.left, key);
            } else if (key.compareTo(cur.k) > 0) {
                cur.right = delete(cur.right, key);
            } else {
                if (cur.right == null && cur.left == null) {
                    cur = null;
                } else if (cur.right == null) {
                    cur = cur.left;
                } else if (cur.left == null) {
                    cur = cur.right;
                } else {
                    AVLNode<K, V> right = cur.right;
                    while (right.left != null) {
                        right = right.left;
                    }
                    cur.right = delete(cur.right, right.k);
                    right.left = cur.left;
                    right.right = cur.right;
                    cur = right;
                }
            }
            if (cur != null) {
                cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0)) + 1;
            }
            return maintain(cur);
        }

        private AVLNode<K, V> findLastIndex(K key) {
            AVLNode<K, V> cur = root;
            AVLNode<K, V> pre = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    pre = cur;
                    break;
                } else if (key.compareTo(cur.k) > 0) {
                    pre = cur;
                    cur = cur.right;
                } else {
                    pre = cur;
                    cur = cur.left;
                }
            }
            return pre;
        }

        private AVLNode<K, V> findLastNoSmallIndex(K key) {
            AVLNode<K, V> cur = root;
            AVLNode<K, V> ans = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    ans = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return ans;
        }

        private AVLNode<K, V> findLastNoBigIndex(K key) {
            AVLNode<K, V> cur = root;
            AVLNode<K, V> ans = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.left;
                }else{
                    ans = cur;
                    cur = cur.right;
                }
            }
            return ans;
        }
        public int size() {
            return size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.k) == 0 ? true : false;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                lastNode.v = value;
            } else {
                size++;
                root = add(root, key, value);
            }
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                return lastNode.v;
            }
            return null;
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.k;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.k;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.k;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.k;
        }

    }


}


















