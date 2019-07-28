package homework3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ex20 {

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

        //删除指定位置的元素
        public Item delete(int k) {
            if (isEmpty()) throw new RuntimeException("所删除的队列为空!");
            if (k > N) throw new RuntimeException("要删除的元素不存在！");
            if (N == 1|| k==1) return dequeue();
            Node find = first;
            for (int i = 1; i < k-1; i++) {
                find = find.next;
            }
            Item item = find.next.item;
            find.next = find.next.next;
            if (k == N) last = find;
            N--;
            return item;
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
        Queue<String> queue = new Queue<>();
        //queue.delete(1);
        queue.enqueue("aa");
        /*for (String s : queue) {
            StdOut.print(s+" ");
        }
        StdOut.println();
        StdOut.println("=================================");
        StdOut.println("删除的元素:"+queue.delete(1));
        for (String s : queue) {
            StdOut.print(s + " ");
        }*/
        /*StdOut.println();
        StdOut.println("=================================");*/
        queue.enqueue("bb");
        queue.enqueue("cc");
        queue.enqueue("dd");
        /*for (String s : queue) {
            StdOut.print(s+" ");
        }
        StdOut.println();
        StdOut.println("=================================");*/
        //queue.delete(3);
        StdOut.println("删除的元素:"+queue.delete(3));
        for (String s : queue) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("=================================");
    }
}
