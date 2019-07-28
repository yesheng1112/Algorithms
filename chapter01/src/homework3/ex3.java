package homework3;

import edu.princeton.cs.algs4.StdOut;

public class ex3 {
    static class Stack<Item>{
        //栈顶（最近添加的元素）
        private Node first;

        //元素数量
        private int N;

        private class Node {
            //定义的节点的嵌套类
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null; //或:N==0
        }

        public int size() {
            return N;
        }

        public void push(Item item) {
            //向栈顶添加元素
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        @SuppressWarnings("unchecked")
        public Item pop() {
            //从栈顶删除元素
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }

    }

    public static void pops(Stack stack,int i) {
        for (int j = 0; j < i; j++) {
            StdOut.print(stack.pop()+" ");
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        StdOut.println("===========");
        s.push(0);
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        pops(s,5);
        s.push(5);
        s.push(6);
        s.push(7);
        s.push(8);
        s.push(9);
        pops(s,5);
        StdOut.println();

        StdOut.println("===========");
        s.push(0);
        s.push(1);
        s.push(2);
        pops(s,1);
        s.push(3);
        s.push(4);
        s.push(5);
        pops(s,1);
        s.push(6);
        pops(s,1);
        s.push(7);
        pops(s,2);
        s.push(8);
        pops(s,1);
        s.push(9);
        pops(s,4);
        StdOut.println();

        StdOut.println("===========");
        s.push(0);
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        pops(s,5);
        s.push(5);
        pops(s,1);
        s.push(6);
        pops(s,1);
        s.push(7);
        pops(s,1);
        s.push(8);
        pops(s,1);
        s.push(9);
        pops(s,1);
        StdOut.println();

        StdOut.println("===========");
        s.push(0);
        s.push(1);
        pops(s,1);
        s.push(2);
        pops(s,1);
        s.push(3);
        pops(s,1);
        s.push(4);
        pops(s,1);
        s.push(5);
        pops(s,1);
        s.push(6);
        pops(s,1);
        s.push(7);
        s.push(8);
        s.push(9);
        pops(s,4);
        StdOut.println();

        StdOut.println("===========");
        s.push(0);
        s.push(1);
        s.push(2);
        pops(s,2);
        s.push(3);
        s.push(4);
        pops(s,2);
        s.push(5);
        s.push(6);
        pops(s,2);
        s.push(7);
        s.push(8);
        pops(s,2);
        s.push(9);
        pops(s,2);
    }
}
