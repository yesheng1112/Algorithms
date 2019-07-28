package homework3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ex2 {

    static class Stack<Item> implements Iterable<Item>{
        //栈顶（最近添加的元素）
        private Node first;

        //元素数量
        private int N;

        private class Node {
            //定义的节点的嵌套类
            Item item;
            Stack.Node next;
        }

        public boolean isEmpty() {
            return first == null; //或:N==0
        }

        public int size() {
            return N;
        }

        public void push(Item item) {
            //向栈顶添加元素
            Stack.Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        @SuppressWarnings("unchecked")
        public Item pop() {
            //从栈顶删除元素
            Item item = first.item;
            first = first.next;
            N--;
            return item;
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

            @SuppressWarnings("unchecked")
            @Override
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
