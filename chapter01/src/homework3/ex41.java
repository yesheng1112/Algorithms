package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex41 {

    static class Queue<Item> {

        private int N;

        private Queue p;

        private Node first;

        private Node last;

        private class Node {
            Item item;
            Node next;
        }

        public Queue() {
        }

        public Queue(Queue p) {
            copyQueue(p);
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void enqueue(Item item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
            N++;
        }

        public Item dequeue() {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        private Queue copyQueue(Queue<Item> newQueue) {
            newQueue = new Queue<>();
            Node find = first;
            for (int i = 0; i < N; i++) {
                newQueue.enqueue(find.item);
                find = find.next;
            }
            return newQueue;
        }

        public void print() {
            Node find = first;
            for (int i = 0; i < size(); i++) {
                if (i == size() - 1) StdOut.print(find.item);
                else StdOut.print(find.item + "->");
                find = find.next;
            }
            StdOut.println();
        }
    }

    public static void test() {
        Queue<Integer> queue1 = new Queue<>();
        for (int i = 0; i < 10; i++) {
            queue1.enqueue(i);
            StdOut.print("queue1:");
            queue1.print();
        }
        for (int i = 0; i < 9; i++) {
            queue1.dequeue();
            StdOut.print("queue1:");
            queue1.print();
        }

        Queue<Integer> queue2 = new Queue<>(queue1);
        for (int i = 0; i < 10; i++) {
            queue2.enqueue(i);
            StdOut.print("queue2:");
            queue2.print();
        }
        for (int i = 0; i < 9; i++) {
            queue2.dequeue();
            StdOut.print("queue2:");
            queue2.print();
        }

    }

    public static void main(String[] args) {
        test();
    }

}
