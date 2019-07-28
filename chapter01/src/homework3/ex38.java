package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex38 {
    interface GeneralizedQueue<Item> {
        boolean isEmpty();

        void insert(Item x);

        Item delete(int k);

        void print();
    }

    static class LinkList<Item> implements GeneralizedQueue<Item> {

        public LinkList() {

        }

        private int size;

        private Node first;
        private Node last;

        private class Node {
            Item item;
            Node next;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        @Override
        public void insert(Item x) {
            Node oldLast = last;
            last = new Node();
            last.item = x;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
            size++;
        }

        private Item dequeue() {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            size--;
            return item;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Item delete(int k) {
            if (isEmpty()) return null;
            if (k > size) return null;
            Node find = first;
            Item item = (Item) new Object();
            for (int i = 0; i < size; i++) {
                if (i == k - 1) {
                    if (k == size) {
                        item = last.item;
                        last = find;
                    }
                    else if (i == 0) return dequeue();
                    else {
                        item = find.next.item;
                        find.next = find.next.next;
                    }
                }
                find = find.next;
            }
            size--;
            return item;
        }

        @Override
        public void print() {
            Node find = first;
            for (int i = 0; i < size; i++) {
                StdOut.print(find.item + " ");
                find = find.next;
            }
            StdOut.println();
        }
    }

    static class ResizingArrayQueue<Item> implements GeneralizedQueue<Item> {

        @SuppressWarnings("unchecked")
        private Item[] arrayQueue = (Item[]) new Object[1];

        private int head;

        private int tail;

        private int size;

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void resize(int max) {
            @SuppressWarnings("unchecked")
            Item[] newArrayQueue = (Item[]) new Object[max];
            if (size > 0) System.arraycopy(arrayQueue, head, newArrayQueue, 0, size);
            arrayQueue = newArrayQueue;
        }

        @Override
        public void insert(Item x) {
            if (tail == arrayQueue.length) resize(arrayQueue.length * 2);
            arrayQueue[tail++] = x;
            size++;
        }

        private Item deleteHead() {
            if (size == arrayQueue.length/4) resize(arrayQueue.length / 2);
            Item item = arrayQueue[head++];
            size--;
            return item;
        }

        @Override
        public Item delete(int k) {
            if (isEmpty()) return null;
            if (k > size) return null;
            if (k == 1) return deleteHead();
            Item item = null;
            int index;
            for (int i = head; i < tail; i++) {
                k--;
                if (k == 0) {
                    item = arrayQueue[i];
                    index = i;
                    shiftArray(index);
                }
            }
            return item;
        }

        //移位和改变数组的大小
        private void shiftArray(int index) {
            for (int i = index; i < tail; i++) {
                arrayQueue[i] = arrayQueue[i + 1];
            }
            arrayQueue[--tail] = null;
            size--;
            if (size == arrayQueue.length / 4) resize(arrayQueue.length / 2);
        }

        @Override
        public void print() {
            for (int i = head; i < tail; i++) {
                StdOut.print(arrayQueue[i] + " ");
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        GeneralizedQueue<Integer> queue = new LinkList<>();
        for (int i = 0; i < 20; i++) {
            queue.insert(i);
        }
        StdOut.println("初始化的链表队列:");
        queue.print();

        StdOut.println("===============================================================");
        StdOut.println("删除头结点:"+queue.delete(1));
        queue.print();

        StdOut.println("===============================================================");
        StdOut.println("删除尾结点:"+queue.delete(19));
        queue.print();

        StdOut.println("===============================================================");
        StdOut.println("删除中间结点:"+queue.delete(10));
        queue.print();

        queue = new ResizingArrayQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.insert(i);
        }

        StdOut.println("===============================================================");
        StdOut.println("初始化的数组队列:");
        queue.print();

        StdOut.println("===============================================================");
        StdOut.println("删除删除第一元素:"+queue.delete(1));
        queue.print();

        StdOut.println("===============================================================");
        StdOut.println("删除最后一个元素:"+queue.delete(19));
        queue.print();

        StdOut.println("===============================================================");
        StdOut.println("删除中间一个元素:"+queue.delete(10));
        queue.print();
    }
}
