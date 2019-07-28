package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex48 {

    static class Deque<Item> {

        private Node first;
        private Node last;

        private class Node {
            Item item;
            Node pre;
            Node next;
        }

        private int N;

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void pushLeft(Item item) {
            Node oldLeft = first;
            first = new Node();
            first.item = item;
            first.pre = null;
            if (isEmpty()) last = first;
            else {
                first.next = oldLeft;
                oldLeft.pre = first;
            }
            N++;
        }

        public Item popRight() {
            Item item = last.item;
            last.pre.next = null;
            last = last.pre;
            if (isEmpty()) first = null;
            N--;
            return item;
        }

        public void pushRight(Item item) {
            Node oldRight = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else {
                last.pre = oldRight;
                oldRight.next = last;
            }
            N++;
        }

        public Item popLeft() {
            Item item = first.item;
            first.next.pre = null;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        public void print(boolean left) {
            Node find;
            if (left) find = first;
            else find = last;
            for (int i = 0; i < N; i++) {
                if (i == N - 1) StdOut.print(find.item);
                else StdOut.print(find.item + "->");
                find = left ? find.next : find.pre;
            }
            StdOut.println();
        }
    }

    public static void test() {
        Deque<Integer> leftStack = new Deque<>();
        Deque<Integer> rightStack = new Deque<>();

        for (int i = 0; i < 10; i++) {
            leftStack.pushLeft(i);
            leftStack.print(true);
        }

        for (int i = 0; i < 9; i++) {
            leftStack.popRight();
            leftStack.print(true);
        }

        for (int i = 0; i < 10; i++) {
            rightStack.pushRight(i);
            rightStack.print(false);
        }

        for (int i = 0; i < 9; i++) {
            rightStack.popLeft();
            rightStack.print(false);
        }
    }

    public static void main(String[] args) {
        test();
    }
}


