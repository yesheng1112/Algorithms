package homework4;

import edu.princeton.cs.algs4.StdOut;

public class ex29 {

    /*
     * 思路 :
     *
     * 使用两个栈来实现 Steque
     * stack1 提供 push pop 操作支持
     * stack2 提供 enqueue 操作支持
     * 在每次 push 和 pop 操作时，都检查 stack2 是否有 enqueue 的元素，如果有的话，就把他们全部 pop 进 stack1
     * 同时，在每次进行 enqueue 操作时，检查 stack1 是否已经有入栈元素，如果有的话，就把他们全部倒进 stack2
     * 以方便从栈底添加元素，模拟 enqueue 操作
     *
     */
    static class Stack<T> {

        private Node first;
        private int N;

        private class Node {
            Node next;
            T item;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void push(T item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        public T pop() {
            T item = first.item;
            first = first.next;
            N--;
            return item;
        }
    }

    static class Steque<T> {

        Stack<T> stack1 = new Stack<>();
        Stack<T> stack2 = new Stack<>();

        public int size() {
            return stack1.size() + stack2.size();
        }

        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        void stack1AllPopToStack2() {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }

        void stack2AllPopToStack1() {
            while (!stack2.isEmpty())
                stack1.push(stack2.pop());
        }

        void push(T item) {
            stack2AllPopToStack1();
            stack1.push(item);
        }

        T pop() {
            stack2AllPopToStack1();
            return stack1.pop();
        }

        void enqueue(T item) {
            stack1AllPopToStack2();
            stack2.push(item);
        }
    }

    public static void main(String[] args) {
        Steque<Integer> steque = new Steque<>();
        for (int i = 0; i < 5; i++)
            steque.push(i);
        for (int i = 5; i < 10; i++)
            steque.enqueue(i);
        StdOut.printf("Steque has %d elements", steque.size());
        while (!steque.isEmpty())
            StdOut.printf("pop %d from steque, now steque has %d elements\n",
                    steque.pop(), steque.size());
    }

}
