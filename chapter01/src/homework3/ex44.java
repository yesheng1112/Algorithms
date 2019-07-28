package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex44 {

    static class Stack<Item> {

        private Node first;

        private int N;

        private class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        public Item pop() {
            if (isEmpty()) return null;
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }
    }

    static class Buffer {

        Stack<Character> left = new Stack<>();

        Stack<Character> right = new Stack<>();

        public Buffer() {
        }

        public void insert(char c) {
            left.push(c);
        }

        public char delete() {
            return left.pop();
        }

        //光标往左移动几个位置，就将左边的栈出几个到右边去
        public void left(int k) {
            for (int i = 0; i < k; i++) {
                right.push(left.pop());
            }
        }

        //光标往右移动几个位置，就将右边的栈出几个到左边去
        public void right(int k) {
            for (int i = 0; i < k; i++) {
                left.push(right.pop());
            }
        }

        public int size() {
            return left.size() + right.size();
        }

        public void print() {
            int rightSize = right.size();
            for (int i = 0; i < rightSize; i++) {
                left.push(right.pop());
            }
            int leftSize = left.size();
            for (int i = 0; i < leftSize; i++) {
                right.push(left.pop());
            }
            rightSize = right.size();
            for (int i = 0; i < rightSize; i++) {
                StdOut.print(right.pop() + " ");
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        buffer.insert('h');
        buffer.insert('e');
        buffer.insert('l');
        buffer.insert('l');
        buffer.insert('o');
        buffer.insert('w');
        buffer.insert('o');
        buffer.insert('r');
        buffer.insert('l');
        buffer.insert('d');

        buffer.left(5);
        buffer.insert(',');
        buffer.right(5);
        buffer.insert('.');
        buffer.print();
    }
}
