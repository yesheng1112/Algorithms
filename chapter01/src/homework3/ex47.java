package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex47 {
    static class Steque<Item> {

        private Node first;
        private Node last;

        private int N;

        private class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            if (isEmpty()) last = first;
            else first.next = oldFirst;
            N++;
        }

        public Item pop() {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        public void enqueue(Item item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
            N++;
        }

        public void catenation(Steque<Item> steque) {
            last.next = steque.first;
            N += steque.size();
        }

        public void print() {
            Node find = first;
            for (int i = 0; i < N; i++) {
                if (i == N-1) StdOut.print(find.item);
                else StdOut.print(find.item+"->");
                find = find.next;
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        Steque<Integer> steque = new Steque<>();
        steque.push(4);
        steque.push(3);
        steque.push(2);
        steque.push(1);
        steque.enqueue(5);
        steque.enqueue(6);
        StdOut.print("Steque1:");
        steque.print();

        Steque<Integer> steque2 = new Steque<>();
        steque2.enqueue(7);
        steque2.enqueue(8);
        steque2.enqueue(9);
        steque2.enqueue(10);
        steque2.enqueue(11);
        steque2.enqueue(12);
        StdOut.print("Steque2:");
        steque2.print();

        StdOut.print("合并:");
        steque.catenation(steque2);
        steque.print();
    }
}
