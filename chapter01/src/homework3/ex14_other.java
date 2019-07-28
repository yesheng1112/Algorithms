package homework3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex14_other {
    /*
     * 思路：
     *
     * 实现方式很简单，只需要用一个变量 size 记录元素个数，并在每次入队时判断
     * 如果此时 size 已经等于数组长度，那么就进行一次扩容
     * 需要注意的是把旧元素搬迁到新数组时，需要一点小技巧，
     * 因为在旧数组中的队列可能头元素的索引大于尾元素的索引，“环绕式添加"
     *
     */
    static class Queue<T> {
        @SuppressWarnings("unchecked")
        private T[] items = (T[]) new Object[1];

        //head为头结点 tail为尾结点 size为存了多少元素
        private int head, tail, size;

        void enqueue(T it) {
            //当存储的元素个数等于数组的长度，就进行扩容,进行一次扩容后tail就等于size
            if (size == items.length) resize(items.length * 2);
            //在尾结点添加
            items[tail] = it;
            tail = (tail + 1) % items.length;
            size++;
            StdOut.println(this);
        }

        T dequeue() {
            if (isEmpty()) throw new RuntimeException("queue is empty");
            size--;
            //记录头结点的元素
            T deq = items[head];
            //让垃圾回收器回收
            items[head] = null;
            //求出新的结点
            head = (head + 1) % items.length;
            //减少容量的时候，head等于0
            if (size > 0 && size == items.length / 4) resize(items.length / 2);
            StdOut.println(this);
            return deq;
        }

        boolean isEmpty() {
            return size == 0;
        }

        @SuppressWarnings("unchecked")
        void resize(int len) {
            T[] newItem = (T[]) new Object[len];
            int cur = head, k = 0;
            do {
                newItem[k++] = items[cur];
                cur = (cur + 1) % items.length;
            } while (cur != tail);
            head = 0;
            tail = size;
            items = newItem;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            for (int i = 0; i < items.length; i++) {
                sb.append(String.format(" %-2s |", items[i] == null ? " " : items[i]));
            }
            sb.append(String.format("     <<< head : %d  tail : %d >>>", head, tail));
            return sb.toString();
        }
    }

    public static boolean[] sourceArr() {
        boolean[] optrs = new boolean[20];
        for (int i = 0; i < 20; i++)
            optrs[i] = StdRandom.bernoulli(0.4);
        return optrs;
    }

    public static void main(String[] args) {
        /*boolean[] operationTable = sourceArr();
        Queue<Integer> queue = new Queue<Integer>();
        int k = 0;
        queue.enqueue(k++);
        queue.enqueue(k++);
        queue.enqueue(k++);
        queue.enqueue(k++);
        queue.enqueue(k++);
        queue.enqueue(k++);
        try {
            for (int i = 0; i < 20; i++)
                if (operationTable[i])
                    queue.enqueue(k++);
                else
                    queue.dequeue();
        } catch (Exception e) {
            StdOut.println("ahead of schedule");
        }
        StdOut.println("game over");*/
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
    }
}