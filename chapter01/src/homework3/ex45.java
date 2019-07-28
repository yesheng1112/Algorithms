package homework3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
 * 思路 :
 *
 * 判断混合序列是否会向下溢出很简单，我们可以遍历整个字符串，读到非减号就加一，代表栈中的元素数量，
 * 读到减号就减一，代表 pop 出一个元素，执行一次迭代后，判断栈容量是否为负数，如果是，那么就是个非法的混合序列
 *
 * 判断某个给定序列是否能由栈产生
 * 我们首先用一个 i 来表示遍历整个字符串的索引，用 j 来表示需要 push 进栈的元素，
 *
 * 如果当前读到的值不等于栈顶元素的值，那么就 push(j++)
 * 如果当前读到的值等于栈顶元素的值，那么就将其 pop(), 同时 i++
 * 循环退出的条件时，必须 i < 字符串长度，并且 j <= 字符串长度
 *
 *  退出循环后，判断栈是否为空，如果是，那么就是一个可能由栈操作产生的序列，否则就不是
 */
public class ex45 {
    static class Stack<T> {

        private Node first;

        private int N;

        private class Node {
            T item;
            Node next;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void push(T item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        public T pop() {
            T item = first.item;
            first = first.next;
            N--;
            return item;
        }

        public T peek() {
            if (isEmpty()) return null;
            return first.item;
        }

    }

    static String[] operations = new String[20];

    static {
        for (int i = 0; i < 10; i++)
            operations[i] = i + "";
        for (int i = 10; i < 20; i++)
            operations[i] = "-";
    }

    public static String[] shuffle(String[] operations) {
        for (int i = 0; i < operations.length; i++) {
            int r = i + StdRandom.uniform(operations.length - i);
            String temp = operations[r];
            operations[r] = operations[i];
            operations[i] = temp;
        }
        return operations;
    }

    public static boolean isStackOperationsUnderflow(String[] optrs) {
        int count = 0;
        for (String s : optrs) {
            if (s.equals("-")) count--;
            else count++;
            if (count < 0) return true;
        }
        return false;
    }

    public static void isSequenceValid(String[] sequence) {
        for (String s : sequence)
            StdOut.print(s + " ");
        StdOut.print(" : ");
        int i = 0, j = 0;
        Stack<String> stack = new Stack<>();
        while (i < sequence.length && j <= sequence.length) {
            if (!sequence[i].equals(stack.peek())) stack.push(Integer.toString(j++));
            else {
                stack.pop();
                i++;
            }
        }
        StdOut.println(stack.isEmpty());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String[] optrs = shuffle(operations);
            StdOut.println(Arrays.toString(optrs) + " : " + isStackOperationsUnderflow(optrs));
        }
        isSequenceValid("4 3 2 1 0 9 8 7 6 5".split(" "));
        isSequenceValid("4 6 8 7 5 3 2 9 0 1".split(" "));
        isSequenceValid("2 5 6 7 4 8 9 3 1 0".split(" "));
        isSequenceValid("4 3 2 1 0 5 6 7 8 9".split(" "));
        isSequenceValid("1 2 3 4 5 6 9 8 7 0".split(" "));
        isSequenceValid("0 4 6 5 3 8 1 7 2 9".split(" "));
        isSequenceValid("1 4 7 9 8 6 5 3 0 2".split(" "));
        isSequenceValid("2 1 4 3 6 5 8 7 9 0".split(" "));
    }
}