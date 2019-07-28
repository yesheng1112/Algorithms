package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex32 {

    static class Steque<Item> {

        private int N;

        private Node first;

        private Node last;

        private class Node {
            Item item;
            Node next;
        }

        private boolean isEmpty() {
            return N == 0;
        }

        private int size() {
            return N;
        }

        //就是往头结点插入元素
        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            if (isEmpty()) last = first;
            else first.next = oldFirst;
            N++;
        }

        //从尾结点插入元素
        public void enqueue(Item item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
            N++;
        }

        //从头结点删除元素
        public Item pop() {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        public void printSteque(Node first) {
            if (first.next == null) {
                StdOut.println(first.item);
                return;
            }
            StdOut.print(first.item + " -> ");
            printSteque(first.next);
        }
    }

    public static void main(String[] args) {
        Steque<Integer> steque = new Steque<>();
        steque.push(4);
        steque.push(3);
        steque.push(2);
        steque.push(1);
        steque.enqueue(5);
        StdOut.print("初始化:");
        steque.printSteque(steque.first);

        steque.pop();
        StdOut.print("出栈:");
        steque.printSteque(steque.first);
    }
}
