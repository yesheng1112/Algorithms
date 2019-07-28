package homework3;

import java.util.Iterator;

public class ex12 {
    static class Stack<Item> implements Iterable<Item> {

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

        public Stack copy(Stack<String> stack) {
            Stack<String> stringStack = new Stack<>();
            for (String s : stack) {
                stringStack.push(s);
            }
            return stringStack;
        }

        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<Item> {

            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }

        }
    }

    public static void main(String[] args) {
        Stack<String> ss = new Stack<>();
        ss.push("aa");
        ss.push("bb");
        ss.push("cc");
        ss.push("dd");
        Stack newSs = ss.copy(ss);
        System.out.println(ss == newSs);
    }
}
