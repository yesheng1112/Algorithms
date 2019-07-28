package homework4;

import edu.princeton.cs.algs4.StdOut;

public class ex28 {
    static class Queue<T> {

        private int size;
        private Node head, tail;

        private class Node {
            Node next;
            T item;
        }

        int size() {
            return size;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void enqueue(T item) {
            Node oldTail = tail;
            tail = new Node();
            tail.item = item;
            tail.next = null;
            if (isEmpty()) head = tail;
            else oldTail.next = tail;
            size++;
        }

        T dequeue() {
            if (isEmpty())
                throw new RuntimeException("dequeue from a empty queue");
            T del = head.item;
            head = head.next;
            if (isEmpty()) tail = null;
            size--;
            return del;
        }

        public String toString() {
            if (isEmpty()) return "empty queue";
            Node cur = head;
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            for (int i = 0; i < size; i++) {
                sb.append(String.format(" %3s |", head.item));
                cur = cur.next;
            }
            return sb.toString();
        }
    }

    static class Stack<T> {
        private Queue<T> queue = new Queue<>();

        boolean isEmpty() {
            return queue.isEmpty();
        }

        int size() {
            return queue.size();
        }

        void push(T item) {
            queue.enqueue(item);
        }

        T pop() {
            if (isEmpty())
                throw new RuntimeException("pop from a empty stack");
            for (int i = 0; i < queue.size() - 1; i++)
                queue.enqueue(queue.dequeue());
            return queue.dequeue();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++)
            stack.push(i);
        StdOut.printf("Stack size is %d\n", stack.size());
        while (!stack.isEmpty())
            StdOut.printf("pop %d from stack, now stack size is %d\n", stack.pop(), stack.size());
    }
}
