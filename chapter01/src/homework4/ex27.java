package homework4;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class ex27 {
    /*
     * 思路 :
     *
     * 用一个栈来专门负责入队，一个栈来负责出队，当需要出队而出队栈没有元素时，把入队栈的所有元素倒入出队栈
     * 如果需要出队并且出队栈有元素时，则直接把出队栈栈顶元素弹出
     *
     * 运行后可以看到均摊图像绘制
     *
     */
    static class Stack<T> {

        private int total;
        private int cost;
        private int size;
        private Node first;


        private class Node {
            T item;
            Node next;

        }

        int total() {
            return total;
        }

        int cost() {
            return cost;
        }

        void clearCost() {
            cost = 0;
        }

        int size() {
            return size;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void push(T item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            size++;
            cost++;
            total++;
        }

        T pop() {
            if (isEmpty())
                throw new RuntimeException("pop from a empty stack");
            T item = first.item;
            first = first.next;
            cost++;
            total++;
            size--;
            return item;
        }

        public String toString() {
            if (isEmpty()) return "empty stack";
            Node cur = first.next;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(cur.item).append(" -> ");
                cur = cur.next;
            }
            return sb.toString();
        }
    }

    static class Queue<T> {
        private int totalOperationsCount;
        private int i = 0;
        private Stack<T> stack1 = new Stack<>();
        private Stack<T> stack2 = new Stack<>();

        boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        int size() {
            return stack1.size() + stack2.size();
        }

        void enqueue(T item) {
            stack2.push(item);
            totalOperationsCount++;
            draw();
        }

        T dequeue() {
            if (isEmpty())
                throw new RuntimeException("dequeue from a empty queue");
            totalOperationsCount++;
            T del;
            if (!stack1.isEmpty()) {
                del = stack1.pop();
                draw();
                return del;
            }
            while (!stack2.isEmpty())
                stack1.push(stack2.pop());
            del = stack1.pop();
            draw();
            return del;
        }

        void draw() {
            i++;
            StdDraw.setPenColor(Color.GREEN);
            StdOut.println("第" + i + "次 cost:" + (stack1.cost() + stack2.cost()));
            StdOut.println("第" + i + "次 totalOperationsCount：" + totalOperationsCount);
            StdDraw.point(totalOperationsCount, stack1.cost() + stack2.cost());
            stack1.clearCost();
            stack2.clearCost();
            StdDraw.setPenColor(Color.RED);
            StdOut.println("第" + i + "次 total：" + ((stack1.total() + stack2.total()) / 2));
            StdOut.println("第" + i + "次 totalOperationsCount：" + totalOperationsCount);
            StdDraw.point(totalOperationsCount, (stack1.total() + stack2.total()) / totalOperationsCount);
        }
    }

    public static void main(String[] args) {
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(-100, 100);
        StdDraw.setPenRadius(.006);
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 100; i++)
            queue.enqueue(i);
        while (!queue.isEmpty()) {
            if (StdRandom.bernoulli(0.3))
                queue.enqueue(1);
            else
                for (int i = 0; i < 30; i++) {
                    if (queue.isEmpty())
                        break;
                    queue.dequeue();
                }

            if (queue.size() <= 90)
                for (int i = 0; i < 10; i++)
                    queue.enqueue(i);

        }

    }
}
