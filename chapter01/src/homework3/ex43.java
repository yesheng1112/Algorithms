package homework3;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class ex43 {
    static class Queue<Item> {

        private int N;

        private Node first;

        private Node last;

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        private class Node {
            Item item;
            Node next;
        }

        public void enqueue(Item item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
            N++;
        }

        public Item dequeue() {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }
    }

    public static void printByRecursivion(File file) {
        StdOut.println("====== Recursion ======");
        int depth = 0;
        print(file, depth);
    }

    //递归
    public static void print(File file, int traceDepth) {
        if (!file.isDirectory()) {
            StdOut.println(fileString(file, traceDepth));
            return;
        }
        StdOut.println(fileString(file, traceDepth));
        for(File f : file.listFiles()) {
            traceDepth++;
            print(f, traceDepth);
            traceDepth--;
        }
    }

    public static void fileList(File file) {
        StdOut.println("====== Queue ======");
        Queue<File> files = new Queue<>();
        Queue<Integer> depths = new Queue<>();
        files.enqueue(file);
        depths.enqueue(0);
        while (!files.isEmpty()) {
            File f = files.dequeue();
            int depth = depths.dequeue();
            StdOut.println(fileString(f, depth));
            if (f.isDirectory()) {
                for (File ff : f.listFiles()) {
                    files.enqueue(ff);
                    depths.enqueue(depth + 1);
                }
            }
        }
    }

    public static String fileString(File file, int depth) {
        String str = "";
        for (int i = 0; i < depth; i++)
            str += "     ";
        return str + "- " + file.getName();
    }

    public static void main(String[] args) {
        File file = new File("D:\\软件");
        printByRecursivion(file);
//        fileList(file);
    }
}
