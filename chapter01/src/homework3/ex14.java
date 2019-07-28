package homework3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ex14 {

    static class ResizingArrayQueue<Item> implements Iterable<Item> {

        @SuppressWarnings("unchecked")
        private Item[] a = (Item[]) new Object[1];

        private int N = 0;

        private int first, last;

        @SuppressWarnings("unchecked")
        private void resize(int max) {
            Item[] temp = (Item[]) new Object[max];
            if (N >= 0) System.arraycopy(a, first, temp, 0, N);
            first = 0;
            last = N;
            a = temp;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void enqueue(Item item) {
            if (N == a.length) resize(2 * a.length);
            a[last++] = item;
            N++;
        }

        public Item dequeue() {
            Item item = a[first];
            a[first] = null;
            first++;
            N--;
            if (N > 0 && N == a.length / 4) resize(a.length / 2);
            return item;
        }

        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<Item> {

            private int i = first;
            private int N = last;

            @Override
            public boolean hasNext() {
                return i < N;
            }

            @Override
            public Item next() {
                return a[i++];
            }
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> resizingArrayQueue = new ResizingArrayQueue<>();
        resizingArrayQueue.enqueue("aa");
        resizingArrayQueue.enqueue("bb");
        resizingArrayQueue.enqueue("cc");
        resizingArrayQueue.enqueue("dd");
        resizingArrayQueue.enqueue("ff");
        StdOut.println(resizingArrayQueue.dequeue());
        StdOut.println(resizingArrayQueue.dequeue());
        StdOut.println(resizingArrayQueue.dequeue());
        StdOut.println(resizingArrayQueue.dequeue());
        StdOut.println("=================");
        for (String s : resizingArrayQueue) {
            StdOut.println(s);
        }
    }
}
