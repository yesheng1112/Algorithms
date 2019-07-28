package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex40 {
    static class Queue<Item> {

        private int size;

        private Node first;

        private class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        //从队头插入
        public void enqueue(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            size++;
        }

        //从队头删除元素
        public void dequeue() {
            first = first.next;
            size--;
        }

        //清除指定的元素
        public void remove(Item item) {
            if (isEmpty()) return;
            if (item.equals(first.item)) {
                dequeue();
                return;
            }
            Node find = first;
            for (int i = 0; i < size - 1; i++) {
                if (item.equals(find.next.item)) {
                    find.next = find.next.next;
                    size--;
                    break;
                }
                find = find.next;
            }

        }

        //读取元素并清除元素
        @SuppressWarnings("unchecked")
        public void readString(String s) {
            String[] result = s.split("");
            for (String ss : result) {
                remove((Item) ss);
                enqueue((Item) ss);
            }
        }

        public void print() {
            Node find = first;
            for (int i = 0; i < size; i++) {
                StdOut.print(find.item + " ");
                find = find.next;
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        String s = "abcdabhijiaddkjikl";
        Queue<String> queue = new Queue<>();
        queue.readString(s);
        queue.print();
    }
}
