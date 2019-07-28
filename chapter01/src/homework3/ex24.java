package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex24 {

    static class Node<Item> {
        Item item;
        Node next;

        Node(Item item) { this.item = item; }
        Node(){}
    }

    static class Stack<Item> {

        private int N;

        private Node first;

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

        @SuppressWarnings("unchecked")
        public Item pop() {
            Item item = (Item) first.item;
            first = first.next;
            N--;
            return item;
        }

        @SuppressWarnings("unchecked")
        public Item peek() {
            if (isEmpty()) return null;
            return (Item) first.item;
        }

        public void printStack() {
            Node find = first;
            for (int i = 0; i < size() - 1; i++) {
                StdOut.print(find.item + "->");
                find = find.next;
            }
            StdOut.print(find.item);
        }

        public void removeAfter(Node remove) {
            if (isEmpty()) return;
            Node find = first;
            int size = size();
            for (int i = 1; i <= size; i++) {
                if (remove.item == find.item) {
                    if (find.next == null) return;
                    else {
                        find.next = null;
                        N -= (N - i);
                        return;
                    }
                }
                find = find.next;
            }
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(9);
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        StdOut.println("开始的栈:");
        stack.printStack();

        //noinspection unchecked
        stack.removeAfter(new Node(7));
        StdOut.println("\n删除后的栈:");
        stack.printStack();

    }
}
