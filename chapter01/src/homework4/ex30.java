package homework4;

import edu.princeton.cs.algs4.StdOut;

public class ex30 {

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

        private int N;
        private Node first;
        private Node last;

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
            if (isEmpty()) last = first;
            N++;
        }

        public void enqueue(T item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
            N++;
        }

        public T pop() {
            T item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }
    }

    static class Deque<T> {
        private Stack<T> stack;
        private Steque<T> steque;

        public Deque() {
            stack = new Stack<>();
            steque = new Steque<>();
        }

        public boolean isEmpty() {
            return stack.isEmpty() && steque.isEmpty();
        }

        public int size() {
            return steque.size() + stack.size();
        }

        public void pushLeft(T item) {
            steque.push(item);
        }

        public void pushRight(T item) {
            steque.enqueue(item);
        }

        public T popLeft() {
            if (!steque.isEmpty())
                return steque.pop();
            else {
                while (!stack.isEmpty())
                    steque.push(stack.pop());
                return steque.pop();
            }
        }

        public T popRight() {
            if (!stack.isEmpty())
                return stack.pop();
            else {
                while (!steque.isEmpty())
                    stack.push(steque.pop());
                return stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.pushLeft(1);
        deque.pushLeft(2);
        deque.pushLeft(3);
        deque.pushLeft(4);
        deque.pushLeft(5);
        deque.pushRight(6);
        deque.pushRight(7);
        StdOut.println(deque.popRight());
        StdOut.println(deque.popLeft());
        StdOut.println(deque.popRight());
        StdOut.println(deque.popLeft());
        StdOut.println(deque.popRight());
        StdOut.println(deque.popLeft());
        StdOut.println(deque.popRight());
    }
}
