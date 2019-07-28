package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex31 {

    static class DoubleQueue<Item> {

        private int N;
        private DoubleNode first;
        private DoubleNode last;

        private class DoubleNode {
            Item item;
            //前驱节点
            DoubleNode pre;
            //后一个节点
            DoubleNode next;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        //在双端队列的头结点插入元素
        public void enqueueByFirst(Item item) {
            DoubleNode oldFirst = first;
            first = new DoubleNode();
            first.item = item;
            first.pre = null;
            if (isEmpty()) last = first;
            else {
                first.next = oldFirst;
                oldFirst.pre = first;
            }
            N++;
        }

        //在双端队列的尾结点插入元素
        public void enqueueByLast(Item item) {
            DoubleNode oldLast = last;
            last = new DoubleNode();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else {
                last.pre = oldLast;
                oldLast.next = last;
            }
            N++;
        }

        //在双端队列的开头删除元素
        public Item dequeueByFirst() {
            Item item = first.item;
            first.next.pre = null;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        //在双端队列的结尾删除元素
        public Item dequeueByLast() {
            Item item = last.item;
            last.pre.next = null;
            last = last.pre;
            if (isEmpty()) first = null;
            N--;
            return item;
        }

        //在指定节点之前插入元素
        public void insertByBefore(Item item1, Item item2) {
            if (isEmpty()) throw new RuntimeException("元素为空");
            if (item1 == first.item) {
                enqueueByFirst(item2);
            }
            DoubleNode node = new DoubleNode();
            node.item = item2;
            if (item1 == last.item) {
                node.next = last;
                node.pre = last.pre;
                last.pre.next = node;
                last.pre = node;
                N++;
            }
            DoubleNode find = first;
            for (int i = 0; i < size(); i++) {
                if (find.item == item1) break;
                find = find.next;
            }
            node.next = find;
            node.pre = find.pre;
            find.pre.next = node;
            find.pre = node;
            N++;
        }

        //在指定节点后面插入元素
        public void insertByAfter(Item item1, Item item2) {
            if (isEmpty()) throw new RuntimeException("元素为空");
            if (item1 == last.item) {
                enqueueByLast(item2);
            }
            DoubleNode node = new DoubleNode();
            node.item = item2;
            if (item1 == first.item) {
                node.pre = first;
                node.next = first.next;
                first.next.pre = node;
                first.next = node;
                N++;
            }

            DoubleNode find = first;
            for (int i = 0; i < size(); i++) {
                if (find.item == item1) break;
                find = find.next;
            }
            node.next = find.next;
            node.pre = find;
            find.next.pre = node;
            find.next = node;
            N++;
        }

        //删除指定的节点
        public void remove(Item item) {
            if (isEmpty()) throw new RuntimeException("双端队列为空");
            DoubleNode find = first;
            for (int i = 0; i < size(); i++) {
                if (item == find.item) break;
                find = find.next;
            }
            if (find == first) dequeueByFirst();
            if (find == last) dequeueByLast();
            find.next.pre = find.pre;
            find.pre.next = find.next;
            find.next = null;
            find.pre = null;
            N--;
        }

        //打印节点
        public void printListByFirst(DoubleNode first) {
            if (first.next == null) {
                StdOut.println(first.item);
                return;
            }
            StdOut.print(first.item + " -> ");
            printListByFirst(first.next);
        }

        public void printListByLast(DoubleNode last) {
            if (last.pre == null) {
                StdOut.println(last.item);
                return;
            }
            StdOut.print(last.item + " <- ");
            printListByLast(last.pre);
        }
    }

    public static void main(String[] args) {
        DoubleQueue<Integer> doubleQueue = new DoubleQueue<>();
        doubleQueue.enqueueByFirst(3);
        doubleQueue.enqueueByFirst(2);
        doubleQueue.enqueueByFirst(1);
        doubleQueue.enqueueByFirst(0);
        StdOut.println("从头节点插入元素：");
        doubleQueue.printListByFirst(doubleQueue.first);
        doubleQueue.printListByLast(doubleQueue.last);

        doubleQueue.enqueueByLast(4);
        doubleQueue.enqueueByLast(5);
        doubleQueue.enqueueByLast(6);
        doubleQueue.enqueueByLast(7);
        StdOut.println("从尾节点插入元素：");
        doubleQueue.printListByFirst(doubleQueue.first);
        doubleQueue.printListByLast(doubleQueue.last);

        doubleQueue.insertByBefore(4,-1);
        StdOut.println("从4节点前面插入-1：");
        doubleQueue.printListByFirst(doubleQueue.first);
        doubleQueue.printListByLast(doubleQueue.last);

        doubleQueue.insertByAfter(4,-2);
        StdOut.println("从4节点后面插入-2：");
        doubleQueue.printListByFirst(doubleQueue.first);
        doubleQueue.printListByLast(doubleQueue.last);

        doubleQueue.dequeueByFirst();
        StdOut.println("删除头结点：");
        doubleQueue.printListByFirst(doubleQueue.first);
        doubleQueue.printListByLast(doubleQueue.last);

        doubleQueue.dequeueByLast();
        StdOut.println("删除尾结点：");
        doubleQueue.printListByFirst(doubleQueue.first);
        doubleQueue.printListByLast(doubleQueue.last);

        doubleQueue.remove(-2);
        StdOut.println("删除指定结点-2：");
        doubleQueue.printListByFirst(doubleQueue.first);
        doubleQueue.printListByLast(doubleQueue.last);
    }
}
