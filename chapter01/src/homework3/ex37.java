package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex37 {

    //环形队列
    static class Queue<Item> {

        //环形节点的尾结点，它的下一个节点是它头结点
        private Node last;

        private int size;

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

        public void enqueue(Item item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            if (isEmpty()) last.next = last;
            else {
                last.next = oldLast.next;
                oldLast.next = last;
            }
            size++;
        }

        //生成源数据
        public Queue<Integer> sourceQueue(int N) {
            Queue<Integer> queue = new Queue<>();
            for (int i = 0; i < N; i++) {
                queue.enqueue(i);
            }
            return queue;
        }

        @SuppressWarnings("unchecked")
        public void printKill(int M, Queue<Integer> queue) {
            if (size == 1) {
                StdOut.print(queue.last.item + " ");
                return;
            }
            Node find = (Node) queue.last;
            for (int i = 1; i <= size; i++) {
                if (i == M) {
                    StdOut.print(find.next.item + " ");
                    find.next = find.next.next;
                    last = find;
                    size--;
                    printKill(M, queue);
                }
                find = find.next;
            }
        }
    }

    public static void test(int N, int M) {
        Queue<Integer> queue = new Queue<>();
        queue = queue.sourceQueue(N);
        queue.printKill(M, queue);
    }

    public static void main(String[] args) {
        test(7, 2);
    }
}
