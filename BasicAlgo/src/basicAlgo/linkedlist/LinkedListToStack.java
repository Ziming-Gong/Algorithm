package basicAlgo.linkedlist;

import java.util.Stack;

public class LinkedListToStack {
    public static void main(String[] args) {
        testStack();
    }
    public static class MyStack<V>{
        private LinkedListToQueue.Node<V> head;
        private int size;

        public MyStack() {
            this.head = null;
            size = 0;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public int size(){
            return size;
        }
        public void push(V value){
            LinkedListToQueue.Node<V> cur = new LinkedListToQueue.Node<>(value);
            if( head == null){
                head = cur;
            }else {
                cur.next = head;
                head = cur;
            }
            size ++;
        }
        public V pop(){
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size --;
            }
            return ans;
        }
        public V peek(){
            return head != null ? head.value:null;

        }

    }
    public static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myStack.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myStack.push(num);
                test.push(num);
            } else if (decide < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = test.pop();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = test.pop();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }
}
