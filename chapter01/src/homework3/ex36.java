package homework3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class ex36 {
    static class RandomQueue<Item> implements Iterable<Item> {

        @SuppressWarnings("unchecked")
        private Item[] queue = (Item[]) new Object[1];

        private int head;
        private int tail;

        private int size;

        public RandomQueue() {
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        @SuppressWarnings("unchecked")
        public void resize(int max) {
            Item[] newQueue = (Item[]) new Object[max];
            if (size > 0) System.arraycopy(queue, head, newQueue, 0, size);
            queue = newQueue;
        }

        public void enqueue(Item item) {
            if (tail == size) resize(queue.length * 2);
            queue[tail++] = item;
            size++;
        }

        public Item dequeue() {
            if (size == queue.length / 4) resize(queue.length / 2);
            int index = StdRandom.uniform(head + 1, tail);
            Item temp = queue[index];
            queue[index] = queue[head];
            queue[head] = temp;
            size--;
            return queue[head++];
        }

        public Item sample() {
            int index = StdRandom.uniform(head, tail);
            return queue[index];
        }

        //读取随机队列
        @SuppressWarnings("unchecked")
        public Item[] randomArr() {
            Item[] randomArr = (Item[]) new Object[size];
            System.arraycopy(queue, head, randomArr, 0, size);

            for (int i = 0; i < randomArr.length; i++) {
                int index = StdRandom.uniform(i, randomArr.length);
                Item temp = randomArr[index];
                randomArr[index] = randomArr[i];
                randomArr[i] = temp;
            }
            return randomArr;
        }

        @Override
        public Iterator<Item> iterator() {
            return new RandomQueueIterator<>();
        }

        private class RandomQueueIterator<Item> implements Iterator<Item> {

            @SuppressWarnings("unchecked")
            private Item[] arr = (Item[]) randomArr();
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Item next() {
                return arr[index++];
            }
        }
    }

    public static void main(String[] args) {
        RandomQueue<Integer> queue = new RandomQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }

        for (Integer integer : queue) {
            StdOut.print(integer + " ");
        }

    }
}
