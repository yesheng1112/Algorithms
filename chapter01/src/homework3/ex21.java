package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex21 {

    static class Stack<Item> {

        private Node first;

        private int N;

        private class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty(){
            return first == null;
        }

        public int size(){
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

        public Item peek(){
            if (isEmpty()) return null;
            return first.item;
        }

    }

    public static boolean find(Stack<String> stack,String key) {
        if (stack.isEmpty()) return false;
        Stack.Node find = stack.first;
        for (int i = 1; i <= stack.size(); i++) {
            if (find.item.equals(key)) return true;
            find = find.next;
        }
        return false;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("aa");
        stack.push("bb");
        stack.push("cc");
        stack.pop();
        stack.pop();
        StdOut.println(find(stack,"aa"));
        StdOut.println(find(stack,"dd"));
    }
}
