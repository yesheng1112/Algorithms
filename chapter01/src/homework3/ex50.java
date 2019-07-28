package homework3;

import edu.princeton.cs.algs4.StdOut;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/*
 * 思路 :
 *
 * 在栈中用两个变量分别记录 pop 数和 push 数
 * 在迭代器初始化时，记录下当前的 pop 数和 push 数，调用每个方法前，先检查当前的 push 和 pop
 * 是否等于原先记录的 push 和 pop 数，如果不等，说明在迭代器执行期间，栈执行了 push 或 pop 操作
 * 此时抛出异常即可
 *
 */
public class ex50 {
    static class Stack<T> implements Iterable<T> {
        private class Node {
            T item;
            Node prev, next;

            Node() {
            }

            Node(T item, Node prev, Node next) {
                this.item = item;
                this.prev = prev;
                this.next = next;
            }

            @SuppressWarnings("UnusedReturnValue")
            Node insertAfter(T item) {
                Node n = new Node(item, this, next);
                if (next != null)
                    next.prev = n;
                next = n;
                return n;
            }

            T delete() {
                T del = item;
                item = null;
                if (prev != null)
                    prev.next = next;
                if (next != null)
                    next.prev = prev;
                return del;
            }
        }

        private Node header = new Node();
        @SuppressWarnings("SpellCheckingInspection")
        private Node tailer = new Node();

        {
            header.next = tailer;
            tailer.prev = header;
            header.prev = tailer.next = null;
        }

        private int popCount = 0;
        private int pushCount = 0;

        boolean isEmpty() {
            return header.next == tailer;
        }

        void push(T item) {
            pushCount++;
            header.insertAfter(item);
        }

        T pop() {
            if (isEmpty())
                throw new RuntimeException("pop from a empty stack");
            popCount++;
            return header.next.delete();
        }

        public String toString() {
            if (isEmpty()) return "[empty]";
            StringBuilder sb = new StringBuilder();
            Node cur = header.next;
            while (cur.next != tailer) {
                sb.append(cur.item).append(" -> ");
                cur = cur.next;
            }
            sb.append(cur.item);
            return sb.toString();
        }

        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int popC = Stack.this.popCount;
                private int pushC = Stack.this.pushCount;
                private Node cur = header.next;

                public boolean hasNext() {
                    if (popC != Stack.this.popCount || pushC != Stack.this.pushCount)
                        throw new ConcurrentModificationException();
                    return cur != tailer;
                }

                public T next() {
                    if (popC != Stack.this.popCount || pushC != Stack.this.pushCount)
                        throw new ConcurrentModificationException();
                    T item = cur.item;
                    cur = cur.next;
                    return item;
                }
            };
        }

        static void ConcurrentModificationExceptionTest() {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < 10; i++)
                stack.push(i);
            StdOut.println(stack);

            for (Integer i : stack) {
                if (i == 6)
                    stack.pop();
                else
                    StdOut.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Stack.ConcurrentModificationExceptionTest();
    }
}