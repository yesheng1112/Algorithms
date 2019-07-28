package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex28 {
    static class Node<Item> {
        private Item item;
        private Node next;

        Node() {

        }

        Node(Item item) {
            this.item = item;
        }
    }

    static class Queue<Item>{
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

    }

    public static Integer max(Node first) {
        //如果链表为空直接返回0
        if (first == null) return 0;
        //如果链表的下一个节点为空，就是最后一个节点直接返回
        if (first.next == null) return (Integer) first.item;
        return Math.max((Integer) first.item, max(first.next));
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(new Node(99));
        queue.enqueue(new Node(55));
        queue.enqueue(new Node(101));
        queue.enqueue(new Node(12));
        queue.enqueue(new Node(11));
        queue.enqueue(new Node(16));
        queue.enqueue(new Node(88));
        queue.enqueue(new Node(100));
        queue.enqueue(new Node(120));

        StdOut.print(max(queue.first));
    }
}
