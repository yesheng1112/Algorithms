package homework3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ex6 {

    public static void reverseQueue(Queue<String> queue) {
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty())
            stack.push(queue.dequeue());
        while (!stack.isEmpty())
            queue.enqueue(stack.pop());

        for (String s : queue)
            StdOut.print(s + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        for (int i = 0; i < 10; i++)
            queue.enqueue(String.valueOf(i));
        reverseQueue(queue);
    }
}
