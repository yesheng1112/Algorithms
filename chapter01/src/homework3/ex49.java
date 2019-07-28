package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex49 {

    static class TwoStackQueue<T> {
        @SuppressWarnings("unchecked")
        static class Stack<T> {
            //利用可变的数组实现
            private T[] items = (T[]) new Object[1];
            //栈的长度
            private int size;

            /**
             * 判断是否为空
             *
             * @return 是否为空
             */
            public boolean isEmpty() {
                return size == 0;
            }

            /**
             * @return 栈的长度
             */
            public int size() {
                return size;
            }

            /**
             * 入栈
             *
             * @param item 入栈的元素
             */
            public void push(T item) {
                if (size == items.length) resize(items.length * 2);
                items[size++] = item;
            }

            /**
             * 出栈
             *
             * @return 出栈的元素
             */
            public T pop() {
                T del = items[--size];
                items[size] = null;
                if (size > 0 && size == items.length / 4) resize(items.length / 2);
                return del;
            }

            /**
             * 扩充数组的长度
             *
             * @param newSize 新数组的长度
             */
            void resize(int newSize) {
                T[] newItems = (T[]) new Object[newSize];
                if (size >= 0) System.arraycopy(items, 0, newItems, 0, size);
                items = newItems;
            }

            /**
             * @return 栈的详细信息
             */
            @Override
            public String toString() {
                if (isEmpty()) return "[empty]";
                StringBuilder sb = new StringBuilder();
                sb.append("|");
                for (T item : items) sb.append(String.format(" %2s |", item == null ? " " : item));
                return sb.toString();
            }

        }

        private Stack<T> stack1 = new Stack<>();
        private Stack<T> stack2 = new Stack<>();

        /**
         * @return 两个栈形成队列的长度
         */
        public int size() {
            return stack1.size() + stack2.size();
        }

        /**
         * @return 构成的队列是否为空
         */
        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        /**
         * 入列
         *
         * @param item 入列的参数
         */
        public void enqueue(T item) {
            //如果两个栈都为空，往第一个栈push
            if (stack1.isEmpty() && stack2.isEmpty()) {
                stack1.push(item);
            } else if (stack1.isEmpty()) { //当第一个栈为空的时候
                while (!stack2.isEmpty()) //当第二个栈不为空的时候
                    stack1.push(stack2.pop());//第二栈中所有的元素移动第一个栈中
                stack1.push(item);//填入第一栈中
            } else {
                stack1.push(item);
            }
        }

        /**
         * 出列
         *
         * @return 出列的参数
         */
        public T dequeue() {
            //如果两个栈都为空
            if (stack1.isEmpty() && stack2.isEmpty()) {
                throw new RuntimeException("dequeue from a empty queue");
            } else if (stack1.isEmpty()) {//栈1为空
                return stack2.pop(); //栈2出栈
            } else { //栈一栈二都不为空
                while (stack1.size() > 1) //栈1中保留一个元素
                    stack2.push(stack1.pop());
                return stack1.pop();//栈一出栈
            }
        }

        static void queueTest() {
            StdOut.println("\n========= 两个栈实现一个队列 ============");
            TwoStackQueue<Integer> queue = new TwoStackQueue<>();
            for (int i = 0; i < 10; i++)
                queue.enqueue(i);
            for (int i = 0; i < 5; i++)
                StdOut.print(queue.dequeue() + " ");
            for (int i = 10; i < 20; i++)
                queue.enqueue(i);
            while (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
    }

    /**
     * 六个栈实现的队列
     * @param <T> 泛型
     */
    static class SixStackQueue<T> {
        static class Stack<T> {
            private class Node {
                Node next;
                T item;

                Node(T item, Node next) {
                    this.next = next;
                    this.item = item;
                }
            }

            private int size = 0;
            private String identifier;
            private Node top = null;

            boolean isEmpty() {
                return size == 0;
            }

            int size() {
                return size;
            }

            Stack(String id) {
                identifier = id;
            }

            void push(T item) {
                size++;
                top = new Node(item, top);
            }

            T pop() {
                size--;
                T item = top.item;
                top = top.next;
                return item;
            }

            Stack<T> shallowCopy(String id) {
                Stack<T> copy = new Stack<>(id);
                copy.top = top;
                copy.size = size;
                return copy;
            }

            String description() {
                return description(top);
            }

            private String description(Node first) {
                if (first == null) return "";
                return description(first.next) + " " + first.item;
            }

            public String toString() {
                return "\n" + identifier + " : [" +
                        (!isEmpty() ? description() : "")
                        + " ]\n";
            }
        }

        private boolean isReversing; // 是否正处于翻转操作中
        private int reverseCount; // 队头翻转后需要再次翻转的数目
        private Stack<T> Head = new Stack<>("H"); // 队头
        private Stack<T> Tail = new Stack<>("T"); // 队尾
        private Stack<T> TailReversed = new Stack<>("H'"); // 提供对队尾的翻转操作
        private Stack<T> TailWhenReverse = new Stack<>("T'"); // 提供在对队尾进行翻转时的入队操作
        private Stack<T> HeadReversed = new Stack<>("Hr"); // 提供对队头的翻转操作
        private Stack<T> HeadWhenReverse = new Stack<>("h"); // 提供在队头进行翻转时的出队操作

        void inspect(String id) {
            switch (id) {
                case "H":
                    StdOut.println(Head);
                    break;
                case "T":
                    StdOut.println(Tail);
                    break;
                case "H'":
                    StdOut.println(TailReversed);
                    break;
                case "T'":
                    StdOut.println(TailWhenReverse);
                    break;
                case "Hr":
                    StdOut.println(HeadReversed);
                    break;
                case "h":
                    StdOut.println(HeadWhenReverse);
                    break;
            }
        }

        void inspect() {
            inspect("H");
            inspect("T");
            inspect("H'");
            inspect("T'");
            inspect("Hr");
            inspect("h");
        }

        boolean isEmpty() {
            if (isReversing)
                return HeadWhenReverse.isEmpty();
            return Head.isEmpty();
        }

        void enqueue(T item) {
            if (isReversing) {
                TailWhenReverse.push(item);
                amortizedOp();
                amortizedOp();
            } else {
                if (Head.size() > Tail.size())
                    Tail.push(item);
                else {
                    isReversing = true;
                    Tail.push(item);
                    HeadWhenReverse = Head.shallowCopy("h");
                    amortizedOp();
                    amortizedOp();
                }
            }
        }

        T dequeue() {
            if (isReversing) {
                T item = HeadWhenReverse.pop();
                reverseCount--;
                amortizedOp();
                amortizedOp();
                return item;
            }

            if (Head.size() > Tail.size())
                return Head.pop();
            else {
                isReversing = true;
                T item = Head.pop();
                HeadWhenReverse = Head.shallowCopy("h");
                amortizedOp();
                amortizedOp();
                return item;
            }
        }

        @SuppressWarnings("Duplicates")
        void amortizedOp() {
            if (!isReversing) return;

            if (!Head.isEmpty() && !Tail.isEmpty()) {
                TailReversed.push(Tail.pop());
                HeadReversed.push(Head.pop());
                reverseCount++;
            } else if (Head.isEmpty() && !Tail.isEmpty()) {
                TailReversed.push(Tail.pop());
            } else if (Head.isEmpty() && Tail.isEmpty()) {
                if (reverseCount > 1) {
                    reverseCount--;
                    TailReversed.push(HeadReversed.pop());
                } else if (reverseCount == 1) {
                    isReversing = false;
                    reverseCount--;
                    TailReversed.push(HeadReversed.pop());
                    Head = TailReversed.shallowCopy("H");
                    Tail = TailWhenReverse.shallowCopy("T");
                    HeadReversed = new Stack<>("Hr");
                    TailReversed = new Stack<>("H'");
                    HeadWhenReverse = new Stack<>("h");
                    TailWhenReverse = new Stack<>("T'");

                } else {
                    isReversing = false;
                    Head = TailReversed.shallowCopy("H");
                    Tail = TailWhenReverse.shallowCopy("T");
                    HeadReversed = new Stack<>("Hr");
                    TailReversed = new Stack<>("H'");
                    HeadWhenReverse = new Stack<>("h");
                    TailWhenReverse = new Stack<>("T'");
                }
            }
        }

        static void queueTest() {
            StdOut.println("\n\n\n========= 六个栈实现一个 O(1) 队列 ============");
            SixStackQueue<Integer> queue = new SixStackQueue<>();
            for (int i = 0; i < 15; i++)
                queue.enqueue(i);
            queue.inspect();
            while (!queue.isEmpty()) {
                StdOut.println(queue.dequeue());
            }
        }
    }

    public static void main(String[] args) {
        TwoStackQueue.queueTest();
        SixStackQueue.queueTest();
    }
}
