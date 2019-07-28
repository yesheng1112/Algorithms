package homework4;

import edu.princeton.cs.algs4.StdOut;

public class ex31 {

    /*
     * 思路 :
     *
     * 三个栈的 A B C 功能如下
     *
     * A 负责 pushLeft popLeft
     * C 负责 pushRight popRight
     * B 负责 当 popLeft A 为空，作为 C 将底部一半元素倒入到 A 的临时缓冲区
     *   负责 当 popRight C 为空时，作为 A 将底部一半元素倒入 C 的临时缓冲区
     */
    static class Stack<T> {

        private int N;
        private Node first;

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

    static class Deque<T> {
        private Stack<T> stackLeft;
        private Stack<T> stackMiddle;
        private Stack<T> stackRight;

        public Deque() {
            stackLeft = new Stack<>();
            stackMiddle = new Stack<>();
            stackRight = new Stack<>();
        }

        public int size() {
            return stackLeft.size() + stackRight.size();
        }

        boolean isEmpty() {
            return stackLeft.isEmpty() && stackRight.isEmpty();
        }

        void pushLeft(T item) {
            stackLeft.push(item);
        }

        void pushRight(T item) {
            stackRight.push(item);
        }

        //先从右边弹出一半到中间
        //再从右边把所有元素弹到左边
        //再将中间所有元素弹到右边
        void rightToLeft() {
            int half = stackRight.size() / 2;
            while (half-- > 0) stackMiddle.push(stackRight.pop());
            while (!stackRight.isEmpty()) stackLeft.push(stackRight.pop());
            while (!stackMiddle.isEmpty()) stackRight.push(stackMiddle.pop());
        }

        //先从左边弹出一半到中间
        //再从左边把所有元素弹到右边
        //再将中间所有元素弹到左边
        void leftToRight() {
            int half = stackLeft.size() / 2;
            while (half-- > 0) stackMiddle.push(stackLeft.pop());
            while (!stackLeft.isEmpty()) stackRight.push(stackLeft.pop());
            while (!stackMiddle.isEmpty()) stackLeft.push(stackMiddle.pop());
        }

        T popLeft() {
            if (isEmpty())
                throw new RuntimeException("pop from a empty deque");
            if (stackLeft.isEmpty())
                rightToLeft();
            return stackLeft.pop();
        }

        T popRight() {
            if (isEmpty())
                throw new RuntimeException("pop from a empty deque");
            if (stackRight.isEmpty())
                leftToRight();
            return stackRight.pop();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        // 11 9 7 5 3 1 2 4 6 8 10
        deque.pushLeft(1);
        deque.pushRight(2);
        deque.pushLeft(3);
        deque.pushRight(4);
        deque.pushLeft(5);
        deque.pushRight(6);
        deque.pushLeft(7);
        deque.pushRight(8);
        deque.pushLeft(9);
        deque.pushRight(10);
        deque.pushLeft(11);

        StdOut.println(deque.popLeft());
        StdOut.println(deque.popRight());
        StdOut.println(deque.popLeft());
        StdOut.println(deque.popRight());
        StdOut.println(deque.popLeft());
        StdOut.println(deque.popRight());
        StdOut.println(deque.popLeft());
        StdOut.println(deque.popRight());
        StdOut.println(deque.popLeft());
        StdOut.println(deque.popRight());
        StdOut.println(deque.popLeft());
    }
}
