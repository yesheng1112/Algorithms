package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex18 {
    /*
     * 思路 :
     *
     * 没啥好说的
     */
    static class Node<T> {
        T item;
        Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }

        Node(T item) {
            this(item, null);
        }

        Node() {
            this(null, null);
        }

        //在当前节点后面插入元素
        Node<T> insertAfter(T item) {
            Node<T> n = new Node<>(item, this.next);
            this.next = n;
            return n;
        }

        public String toString() {
            return item.toString();
        }
    }

    static class List<T> {
        //头结点
        private Node<T> header = new Node<>();
        //尾结点
        private Node<T> tailer = new Node<>();
        private int size;

        {
            size = 0;
            header.next = tailer;
            tailer.next = null;
        }

        //在头结点后面插入元素，就是第一个元素
        Node<T> insertAsFirst(T item) {
            header.insertAfter(item);
            size++;
            return header.next;
        }

        Node<T> findPrev(T item) {
            Node<T> n = header.next;
            //当下个节点不等于尾结点的时候
            while (n.next != tailer)
                if (n.next.item.equals(item))
                    return n;
                else
                    n = n.next;
            return null;
        }

        T remove(T item) {
            Node<T> x = findPrev(item);
            T del = x.next.item;

            /*
             * 删除 x 的后续节点
             */
            x.next = x.next.next;
            size--;
            return del;
        }

        int size() {
            return size;
        }

        public String toString() {
            Node<T> n = header.next;
            StringBuilder sb = new StringBuilder();
            while (n.next != tailer) {
                sb.append(n.item + " -> ");
                n = n.next;
            }
            sb.append(n.item);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        StdOut.println("initialize a list structure");
        List<Integer> list = new List<>();
        list
                .insertAsFirst(0)
                .insertAfter(1)
                .insertAfter(2)
                .insertAfter(3)
                .insertAfter(4)
                .insertAfter(5)
                .insertAfter(6)
                .insertAfter(7)
                .insertAfter(8);
        StdOut.println(list);

        StdOut.println("\ndelete node value is 4");
        list.remove(4);

        StdOut.println(list);
    }
}
