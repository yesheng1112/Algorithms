package homework3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ex19 {

    static class Queue<Item> implements Iterable<Item> {

        private int N;
        private Node first;
        private Node last;

        private class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null;
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

        //删除尾结点
        public Item dequeueByLast() {
            if (isEmpty()) return null;
            if (N == 1) {
                Item item = first.item;
                first = null;
                N--;
                return item;
            }
            else{
                Node find = first;
                while (find.next != last) {
                    find = find.next;
                }
                Item item = find.next.item;
                find.next = null;
                last = find;
                N--;
                return item;
            }
        }

        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<Item> {

            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        for (Integer integer : queue) {
            StdOut.print(integer + " ");
        }
        StdOut.println();
        StdOut.println(queue.dequeueByLast());
        StdOut.println(queue.dequeueByLast());
        StdOut.println(queue.dequeueByLast());
        StdOut.println(queue.dequeueByLast());
        for (Integer integer : queue) {
            StdOut.print(integer + " ");
        }
    }

}
