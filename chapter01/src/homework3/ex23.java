package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex23 {
    static class Node<T> {
        T item;
        Node<T> next;
        Node(T item) { this(item, null); }
        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
    /*
     * 在 x 的后面插入一个 t
     */
    public static <T> void insertAfter(Node<T> x, Node<T> t) {
        //指向自己一直死循环
        x.next = t;
        t.next = x.next;

    }
    public static <T> void printList(Node<T> list) {
        if (list == null) return;
        while(list.next != null) {
            StdOut.print(list.item + " -> ");
            list = list.next;
        }
        StdOut.println(list.item);
    }
    public static void main(String[] args) {
        Node<Integer> first =
                new Node<>(0,
                        new Node<>(1,
                                new Node<>(2,
                                        new Node<>(3,
                                                new Node<>(4,
                                                        new Node<>(5,
                                                                new Node<>(6,
                                                                        new Node<>(7))))))));
        StdOut.println("initialize a list");
        printList(first);

        StdOut.println("\nafter insert a new node after the first one");
        insertAfter(first, new Node<>(99));
        printList(first);

        StdOut.println("\nafter insert a new node after the first one");
        insertAfter(first, new Node<>(98));
        printList(first);

        StdOut.println("\nafter insert a new node after the first one");
        insertAfter(first, new Node<>(97));
        printList(first);
    }
}
