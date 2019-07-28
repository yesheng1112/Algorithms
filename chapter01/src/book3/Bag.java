package book3;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    //栈顶（最近添加的元素）
    private Node first;

    //元素数量
    private int N;

    private class Node {
        //定义的节点的嵌套类
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null; //或:N==0
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        //向栈顶添加元素
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
