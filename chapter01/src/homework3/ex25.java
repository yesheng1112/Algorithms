package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex25 {

    static class Node<Item> {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
        }

        Node() {
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

        @SuppressWarnings("unchecked")
        public void enqueue(Item item) {
            Node oldLast = last;
            last = new Node(item);
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

        @SuppressWarnings({"UnnecessaryReturnStatement", "unchecked"})
        public void insertAfter(Node node1, Node node2) {
            if (node1 == null || node2 == null) return;
            if (node1.item == last.item) {
                enqueue((Item) node2.item);
            } else {
                Node find = first;
                int size = size();
                for (int i = 1; i <= size; i++) {
                    if (find.item == node1.item) {
                        node2.next = find.next;
                        find.next = node2;
                        N++;
                        return;
                    }
                    find = find.next;
                }
            }
        }

        public void printQueue() {
            Node find = first;
            for (int i = 0; i < size()-1; i++) {
                StdOut.print(find.item + "->");
                find = find.next;
            }
            StdOut.print(find.item);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        StdOut.println("开始的队列");
        queue.printQueue();

        queue.insertAfter(new Node(4),new Node(6));
        StdOut.println("\n插入后的队列");
        queue.printQueue();

        queue.insertAfter(new Node(5),new Node(7));
        StdOut.println("\n插入后的队列");
        queue.printQueue();
    }

}
