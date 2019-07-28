package homework3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ex13 {
    static class Stack<Item> implements Iterable<Item>{

        private Node first;

        private int N;

        private class Node{
            Item item;
            Node next;
        }

        public boolean isEmpty(){
            return first.next == null;
        }

        public int size() {
            return N;
        }

        public void push(Item item){
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

        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<Item>{

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
        StdOut.println("===================");
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        StdOut.print(stack.pop()+" ");
        stack.push(1);
        StdOut.print(stack.pop()+" ");
        stack.push(2);
        StdOut.print(stack.pop()+" ");
        stack.push(3);
        StdOut.print(stack.pop()+" ");
        stack.push(4);
        StdOut.print(stack.pop()+" ");
        stack.push(5);
        StdOut.print(stack.pop()+" ");
        stack.push(6);
        StdOut.print(stack.pop()+" ");
        stack.push(7);
        StdOut.print(stack.pop()+" ");
        stack.push(8);
        StdOut.print(stack.pop()+" ");
        stack.push(9);
        StdOut.print(stack.pop()+" ");


        StdOut.println("\n===================");
        stack.push(0);
        stack.push(1);
        stack.push(2);
        StdOut.print(stack.pop()+" ");
        stack.push(3);
        stack.push(4);
        stack.push(5);
        StdOut.print(stack.pop()+" ");
        stack.push(6);
        StdOut.print(stack.pop()+" ");
        stack.push(7);
        StdOut.print(stack.pop()+" ");
        StdOut.print(stack.pop()+" ");
        stack.push(8);
        StdOut.print(stack.pop()+" ");
        stack.push(9);
        StdOut.print(stack.pop()+" ");
        StdOut.print(stack.pop()+" ");
        StdOut.print(stack.pop()+" ");
        StdOut.print(stack.pop()+" ");

        StdOut.println("\n===================");
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        StdOut.print(stack.pop()+" ");
        StdOut.print(stack.pop()+" ");
        StdOut.print(stack.pop()+" ");
        StdOut.print(stack.pop()+" ");
        StdOut.print(stack.pop()+" ");
        stack.push(5);
        StdOut.print(stack.pop()+" ");
        stack.push(6);
        StdOut.print(stack.pop()+" ");
        stack.push(7);
        StdOut.print(stack.pop()+" ");
        stack.push(8);
        StdOut.print(stack.pop()+" ");
        stack.push(9);
        StdOut.print(stack.pop()+" ");
    }
}
