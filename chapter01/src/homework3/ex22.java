package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex22 {

    static class Stack<Item> {

        private Node first;
        private int N;

        private class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return N;
        }

        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        public Item pop() {
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }

        public Item peek() {
            if (isEmpty()) return null;
            return first.item;
        }

        public void insertAfterByFirst(Item item) {
            Node node = new Node();
            node.item = item;
            node.next = first.next;
            first.next = node;
            N++;
        }

        public void printList() {
            if (isEmpty()) return;
            Node find = first;
            for (int i = 0; i < size()-1; i++) {
                StdOut.print(find.item + " -> ");
                find = find.next;
            }
            StdOut.print(find.item);
        }

        public static void main(String[] args) {
            Stack<Integer> stack = new Stack<>();
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
            stack.push(5);
            stack.push(6);
            StdOut.println("initialize a list");
            stack.printList();

            StdOut.println("\nafter insert a new node after the first one");
            stack.insertAfterByFirst(99);
            stack.printList();

            StdOut.println("\nafter insert a new node after the first one");
            stack.insertAfterByFirst(98);
            stack.printList();

            StdOut.println("\nafter insert a new node after the first one");
            stack.insertAfterByFirst(97);
            stack.printList();
        }
    }
}
