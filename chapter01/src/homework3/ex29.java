package homework3;


import edu.princeton.cs.algs4.StdOut;

//环形链表
public class ex29 {

    static class Queue<Item>{

        private Node last;
        private int N;

        private class Node{
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size(){
            return N;
        }

        public void enqueue(Item item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            if (isEmpty()) {
                last.next = last;
            }
            else{
                last.next = oldLast.next;
                oldLast.next = last;
            }
            N++;
        }

        public Item dequeue(){
            if(isEmpty()) return null;
            Item item = last.next.item;
            last.next = last.next.next;
            N--;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
    }
}
