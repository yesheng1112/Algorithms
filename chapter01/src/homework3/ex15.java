package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex15 {

    static class Queue<Item> {

        private int N;
        private Node first;
        private Node last;

        private class Node {
            Item item;
            Node next;
            Node pre;
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
            else {
                oldLast.next = last;
                last.pre = oldLast;
            }
            N++;
        }

        public Item dequeue() {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            else first.pre = null;
            N--;
            return item;
        }

        public void printItemByk(int k) {
            if (k > N) throw new RuntimeException("你要查找的不存在");
            Node kNode = last;
            for (int i = 1; i < k; i++) {
                kNode = kNode.pre;
            }
            StdOut.println("查找的字符:" + kNode.item);
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("aa");
        queue.enqueue("bb");
        StdOut.println(queue.dequeue());
        queue.enqueue("cc");
        queue.enqueue("dd");
//        queue.printItemByk(5);
        queue.printItemByk(3);
    }
}
