package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex42 {
    static class Stack<Item> {

        private int N;

        private Node first;

        public Stack() {
        }

        public Stack(Stack<Item> stack) {
            copyStack(stack);
        }

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
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }

        public void copyStack(Stack<Item> newStack) {
            newStack = new Stack<>();
            Node find = first;
            for (int i = 0; i < N; i++) {
                newStack.push(find.item);
                find = find.next;
            }
        }

        public void print() {
            Node find = first;
            for (int i = 0; i < N; i++) {
                if (i == N - 1) StdOut.print(find.item);
                else StdOut.print(find.item + "->");
                find = find.next;

            }
            StdOut.println();
        }
    }

    public static void test() {
        Stack<Integer> stack1 = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack1.push(i);
            StdOut.print("stack1:");
            stack1.print();
        }
        for (int i = 0; i < 9; i++) {
            stack1.pop();
            StdOut.print("stack1:");
            stack1.print();
        }

        Stack<Integer> stack2 = new Stack<>(stack1);
        for (int i = 0; i < 10; i++) {
            stack2.push(i);
            StdOut.print("stack2:");
            stack2.print();
        }
        for (int i = 0; i < 9; i++) {
            stack2.pop();
            StdOut.print("stack2:");
            stack2.print();
        }
    }

    public static void main(String[] args) {
        test();
    }
}
