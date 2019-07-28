package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex46 {
    static class Stack<Item> {

        private Node first;

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
    }

    public static void isSequenceValid(String[] sequence, Stack<String> three) {
        for (String s : sequence)
            StdOut.print(s + " ");
        StdOut.print(" : ");
        int i = 0, j = 0;
        Stack<String> stack = new Stack<>();
        while (i < sequence.length && j <= sequence.length) {
            if (!sequence[i].equals(stack.peek())) stack.push(Integer.toString(j++));
            else {
                if (sequence[i].equals(three.peek())) three.pop();
                stack.pop();
                i++;
            }
        }
        StdOut.println(stack.isEmpty() && three.isEmpty());
    }

    public static void main(String[] args) {
        Stack<String> three = new Stack<>();
        three.push("3");
        three.push("6");
        three.push("9");
        isSequenceValid("4 3 2 1 0 9 8 7 6 5".split(" "),three);
        isSequenceValid("4 6 8 7 5 3 2 9 0 1".split(" "),three);
        isSequenceValid("2 5 6 7 4 8 9 3 1 0".split(" "),three);
        isSequenceValid("4 3 2 1 0 5 6 7 8 9".split(" "),three);
        isSequenceValid("1 2 3 4 5 6 9 8 7 0".split(" "),three);
        isSequenceValid("0 4 6 5 3 8 1 7 2 9".split(" "),three);
        isSequenceValid("1 4 7 9 8 6 5 3 0 2".split(" "),three);
        isSequenceValid("2 1 4 3 6 5 8 7 9 0".split(" "),three);
    }
}
