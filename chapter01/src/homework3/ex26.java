package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex26 {
    static class Node<Item> {
        private Item item;
        private Node next;

        Node() {
        }

        Node(Item item) {
            this.item = item;
        }
    }

    static class Queue<Item> {
        private int N;
        private Node first;
        private Node last;

        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return N;
        }

        public void enqueue(Node newNode) {
            Node oldLast = last;
            last = newNode;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
            N++;
        }

        @SuppressWarnings("unchecked")
        public Item dequeue() {
            Item item = (Item) first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        public void printQueue() {
            Node find = first;
            for (int i = 0; i < size() - 1; i++) {
                StdOut.print(find.item + "->");
                find = find.next;
            }
            StdOut.print(find.item);
        }
    }

    //删除队列中所有节点为key的值
    public static void remove(Queue queue, int key) {
        if (queue.isEmpty()) return;
        if ((Integer) queue.first.item == key) queue.dequeue();
        Node find = queue.first;
        for (int i = 0; i < queue.N-1; i++) {
            if (key == (Integer) find.next.item) break;
            find = find.next;
        }
        if (find == queue.last) return;
        if (find.next == queue.last) {
            find.next = null;
            queue.last = find;
            queue.N--;
        } else {
            find.next = find.next.next;
            queue.N--;
        }
        remove(queue, key);
    }


    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(new Node(1));
        queue.enqueue(new Node(2));
        queue.enqueue(new Node(3));
        queue.enqueue(new Node(4));
        queue.enqueue(new Node(4));
        queue.enqueue(new Node(4));
        queue.enqueue(new Node(4));
        queue.enqueue(new Node(5));
        StdOut.println("开始的队列");
        queue.printQueue();

        remove(queue, 4);
        StdOut.println("\n删除后4的队列");
        queue.printQueue();

    }
}
