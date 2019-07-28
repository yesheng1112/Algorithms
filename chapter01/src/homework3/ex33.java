package homework3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ex33 {
    static class Deque<Item> implements Iterable<Item> {

        private int N;

        private Node first;

        private Node last;

        private class Node {
            Item item;
            Node pre;
            Node next;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        //获取双向队列中的元素数量
        public int size() {
            return N;
        }

        //向左端添加一个新元素
        public void pushLeft(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.pre = null;
            if (isEmpty()) last = first;
            else {
                first.next = oldFirst;
                oldFirst.pre = first;
            }
            N++;
        }

        //向右端添加一个新元素
        public void pushRight(Item item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else {
                last.pre = oldLast;
                oldLast.next = last;
            }
            N++;
        }

        //从左端删除一个元素
        public Item popLeft() {
            Item item = first.item;
            first.next.pre = null;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        //从右端删除一个元素
        public Item popRight() {
            Item item = last.item;
            last.pre.next = null;
            last = last.pre;
            if (isEmpty()) first = null;
            N--;
            return item;
        }

        @Override
        public Iterator<Item> iterator() {
            return new DequeIterator();
        }

        public class DequeIterator implements Iterator<Item> {

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

    static class ResizingArrayDeque<Item> implements Iterable<Item> {

        @SuppressWarnings("unchecked")
        private Item[] items = (Item[]) new Object[2];
        private int size;
        //头结点
        private int head = 0;
        //尾结点
        private int tail = 1;

        //判断是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        //返回数组的长度
        public int size() {
            return size;
        }

        //左边插入
        public void pushLeft(Item item) {
            if (head < 0) pushResize(items.length * 2, true);
            size++;
            //从中间开始插入然后往两边插入
            items[head--] = item;
            StdOut.println(this);
        }

        //右边插入
        public void pushRight(Item item) {
            //尾结点等于数组的长度
            if (tail == items.length) pushResize(items.length * 2, false);
            size++;
            items[tail++] = item;
            StdOut.println(this);
        }

        //左边弹出
        public Item popLeft() {
            //当长度为空的时候
            if (isEmpty()) throw new RuntimeException("pop from a empty deque");
            size--;
            Item pop = items[++head];
            //让垃圾回收器回收到
            items[head] = null;
            //如果数组中一个元素都没有了，让头结点指向0尾结点指向1
            if (size == 0) {
                head = 0;
                tail = 1;
            } else if (size > 0 && size == items.length / 4)//长度等于数组长度的1/4
                popResize(items.length / 2);
            StdOut.println(this);
            return pop;
        }

        //右边弹出
        public Item popRight() {
            //当长度等于0的时候
            if (isEmpty()) throw new RuntimeException("pop from a empty deque");
            size--;
            Item pop = items[--tail];
            //让垃圾回收器回收到
            items[tail] = null;
            //如果数组中一个元素都没有了，让头结点指向0尾结点指向1
            if (size == 0) {
                head = 0;
                tail = 1;
            } else if (size > 0 && size == items.length / 4)//长度等于数组长度的1/4
                popResize(items.length / 2);
            StdOut.println(this);
            return pop;
        }

        /**
         * 从左边扩充
         *
         * @param newSize 新的数组的长度
         * @param left    从那个方向添加元素
         */
        @SuppressWarnings("unchecked")
        void pushResize(int newSize, boolean left) {
            Item[] newItems = (Item[]) new Object[newSize];
            //index取的值是[newSize/4-1,newSize/2-1]集合
            int index = (newSize - size - 1) / 2;
            //cur的取值可能是[newSize/4-1,newSize]的集合
            int cur = left ? index + 1 : index;
            //遍历数组中的所有元素
            /*for (int i = head + 1; i < tail; i++)
                newItems[cur++] = items[i];*/
            System.arraycopy(items, head + 1, newItems, cur, tail - head - 1);
            head = left ? index : index - 1;
            tail = left ? index + size + 1 : index + size;
            items = newItems;
        }

        @SuppressWarnings("unchecked")
        void popResize(int newSize) {
            Item[] newItems = (Item[]) new Object[newSize];
            //index的取值为[newSize/4,newSize/2]之间肯定等于newSize/4
            int index = newSize / 4;
            /*int cur = index;*/
            /*for (int i = head + 1; i < tail; i++)
                newItems[cur++] = items[i];*/
            System.arraycopy(items, head + 1, newItems, index, tail - head - 1);
            head = index - 1;
            tail = index + size;
            items = newItems;
        }

        public Iterator<Item> iterator() {
            return new Iterator<Item>() {
                private int i = head + 1;
                public boolean hasNext() {
                    return i < tail;
                }
                public Item next() {
                    return items[i++];
                }

            };
        }

        public String toString() {
            if (isEmpty()) return "[empty]";
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            for (int i = 0; i < items.length; i++)
                sb.append(String.format("%3s |", items[i] == null ?
                        " " : items[i].toString()));
            sb.append(String.format("      <<<<< head : %d tail : %d >>>>>>", head, tail));
            return sb.toString();
        }
    }

    public static void iteratorTest() {
        ResizingArrayDeque<Integer> deque = new ResizingArrayDeque<Integer>();    // double linked list implementation
        deque.pushLeft(0);
        deque.pushRight(1);
        deque.pushLeft(2);
        deque.pushLeft(3);
        deque.popLeft();
        deque.popRight();
        deque.popLeft();
        deque.pushLeft(99);
        deque.pushRight(98);
        deque.pushLeft(97);
        deque.pushLeft(96);
        deque.pushLeft(95);
        deque.pushRight(94);
        deque.pushLeft(93);
        deque.pushLeft(92);
        deque.popLeft();
        deque.popRight();
        deque.popLeft();
        deque.pushLeft(91);
        deque.pushRight(90);
        deque.pushLeft(89);
        deque.pushLeft(88);

        for (Integer i : deque)
            StdOut.print(String.format("【%d】", i));
        StdOut.println();

        Deque<Integer> deque1 = new Deque<>();    // double linked list implementation
        deque1.pushLeft(0);
        deque1.pushRight(1);
        deque1.pushLeft(2);
        deque1.pushLeft(3);
        deque1.popLeft();
        deque1.popRight();
        deque1.popLeft();
        deque1.pushLeft(99);
        deque1.pushRight(98);
        deque1.pushLeft(97);
        deque1.pushLeft(96);
        deque1.pushLeft(95);
        deque1.pushRight(94);
        deque1.pushLeft(93);
        deque1.pushLeft(92);
        deque1.popLeft();
        deque1.popRight();
        deque1.popLeft();
        deque1.pushLeft(91);
        deque1.pushRight(90);
        deque1.pushLeft(89);
        deque1.pushLeft(88);

        StdOut.println("=============================================================");
        for (Integer i : deque1)
            StdOut.print(String.format("【%d】", i));
        StdOut.println();
    }

    public static void main(String[] args) {
        iteratorTest();
    }

}
