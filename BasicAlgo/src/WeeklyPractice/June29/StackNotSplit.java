package WeeklyPractice.June29;

import java.util.ArrayList;
import java.util.Stack;

// 来自微软
// 请设计一种叫做“栈的管理器”的结构，实现如下6个功能
// 1) void createNewStack() : 可以在该结构中生成一个栈结构，编号从0开始
// 2) void push(int num, int stackIndex) : 将编号为stackIndex的栈里，压入num
// 3) int pop(int stackIndex) : 从编号为stackIndex的栈里，弹出栈顶返回
// 4) int peek(int stackIndex) ：从编号为stackIndex的栈里，返回栈顶但是不弹出
// 5) boolean isEmpty(int statckIndex)：返回编号为stackIndex的栈是否为空
// 6) int stackSize() : 返回一共生成了多少个栈
// 要求：不管用户调用多少次上面的方法，只使用有限几个动态数组(常数个)，完成代码实现
public class StackNotSplit {
    public static class Stacks1 {

        public ArrayList<Stack<Integer>> stacks;

        public Stacks1() {
            stacks = new ArrayList<>();
        }

        public int stackSize() {
            return stacks.size();
        }

        public void createNewStack() {
            stacks.add(new Stack<>());
        }

        public void push(int num, int stackIndex) {
            stacks.get(stackIndex).push(num);
        }

        public int pop(int stackIndex) {
            return stacks.get(stackIndex).pop();
        }

        public boolean isEmpty(int statckIndex) {
            return stacks.get(statckIndex).isEmpty();
        }

        public int peek(int stackIndex) {
            return stacks.get(stackIndex).peek();
        }

    }

    public static class Stacks2 {

        ArrayList<Integer> head;
        ArrayList<Integer> value;
        ArrayList<Integer> last;
        ArrayList<Integer> free;
        private int valueSize;
        private int freeSize;

        public Stacks2() {
            head = new ArrayList<>();
            value = new ArrayList<>();
            last = new ArrayList<>();
            free = new ArrayList<>();
            valueSize = 0;
            freeSize = 0;
        }


        public void createNewStack() {
            head.add(-1);
        }

        public void push(int num, int stackIndex) {
            int lastIndex = head.get(stackIndex);
            if (freeSize > 0) {
                int site = free.get(--freeSize);
                value.set(site, num);
                last.set(site, lastIndex);
                head.set(stackIndex, site);
            } else {
                value.add(num);
                last.add(lastIndex);
                head.set(stackIndex, valueSize++);
            }
        }

        public int pop(int stackIndex) {
            int cur = head.get(stackIndex);
            int l = last.get(cur);
            head.set(stackIndex, l);
            if (freeSize < free.size()) {
                free.set(freeSize, cur);
            } else {
                free.add(cur);
            }
            freeSize++;
            return value.get(cur);

        }

        public int peek(int stackIndex) {
            return head.get(stackIndex) == -1 ? -1 : value.get(head.get(stackIndex));
        }

        public boolean isEmpty(int stackIndex) {
            return head.get(stackIndex) == -1;
        }

        public int stackSize() {
            return head.size();
        }
    }

    public static void main(String[] args) {
        int V = 10000;
        int testTime = 20000;
        System.out.println("测试开始");
        Stacks1 stack1 = new Stacks1();
        Stacks2 stack2 = new Stacks2();
        for (int i = 0; i < testTime; i++) {
            double decide = Math.random();
            if (decide < 0.25) {
                stack1.createNewStack();
                stack2.createNewStack();
            } else {
                int stackSize1 = stack1.stackSize();
                int stackSize2 = stack2.stackSize();
                if (stackSize1 != stackSize2) {
                    System.out.println("栈的数量不一致！");
                    break;
                }
                if (stackSize1 > 0) {
                    int stackIndex = (int) (Math.random() * stackSize1);
                    if (decide < 0.5) {
                        int num = (int) (Math.random() * V);
                        stack1.push(num, stackIndex);
                        stack2.push(num, stackIndex);
                    } else if (decide < 0.75) {
                        if (stack1.isEmpty(stackIndex) != stack2.isEmpty(stackIndex)) {
                            System.out.println(stackIndex + "号栈的是否为空不一致！");
                            break;
                        }
                        if (!stack1.isEmpty(stackIndex)) {
                            if (stack1.pop(stackIndex) != stack2.pop(stackIndex)) {
                                System.out.println(stackIndex + "号栈的弹出数据不一致！");
                                break;
                            }
                        }
                    } else {
                        if (stack1.isEmpty(stackIndex) != stack2.isEmpty(stackIndex)) {
                            System.out.println(stackIndex + "号栈的是否为空不一致！");
                            break;
                        }
                        if (!stack1.isEmpty(stackIndex)) {
                            if (stack1.peek(stackIndex) != stack2.peek(stackIndex)) {
                                System.out.println(stackIndex + "号栈的栈顶数据不一致！");
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("测试结束");
    }


}
