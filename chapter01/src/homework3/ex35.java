package homework3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex35 {
    static class RandomQueue<Item> {

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

        public void printQueue() {
            for (int i = head; i < tail; i++) {
                StdOut.print(queue[i] + ",");
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        RandomQueue<Integer> queue = new RandomQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
        StdOut.println("正常的队列");
        queue.printQueue();

        StdOut.println("===============================");
        StdOut.print("随机弹出一个队列的元素,交换:");
        StdOut.println(queue.dequeue());

        StdOut.println("===============================");
        StdOut.println("随机后的队列，交换");
        queue.printQueue();

        StdOut.println("===============================");
        StdOut.print("随机弹出一个队列的元素,不交换:");
        StdOut.println(queue.sample());

        StdOut.println("===============================");
        StdOut.println("随机后的队列，不交换");
        queue.printQueue();
    }
}
