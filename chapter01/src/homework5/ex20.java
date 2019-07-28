package homework5;

import edu.princeton.cs.algs4.StdOut;

//利用链表实现
public class ex20 {

    static class List{
        private class Node{
            //父节点
            private Node parent;
            //下一个节点
            private Node next;
            //节点的值
            private int value;
            //所有的节点数开始都是1
            private int size = 1;

            //设置父节点
            void setParent(Node n){
                parent = n;
            }

            //返回节点的值
            int value(){
                return value;
            }

            //返回连通的分量
            int size() {
                return size;
            }

            //小树连接到大树的时候，改变连通的分量
            int addSize(int sz){
                return size += sz;
            }

            Node(){}

            Node(int value, Node next) {
                this.value = value;
                //刚开始的父节点是自己
                this.parent = this;
                this.next = next;
            }

            Node insertAfter(int p) {
                return next = new Node(p, next);
            }

            //已连通的节点
            public String toString() {
                return String.format("{%d %d}", value, parent.value);
            }
        }

        private Node header = new Node();

        boolean isEmpty() {
            return header == null;
        }

        Node findNode(int p) {
            Node cur = header.next;
            //找到对应的节点
            while (cur != null && cur.value != p)
                cur = cur.next;
            if (cur == null)
                throw new RuntimeException("out of list's bounds");
            //找到对应节点的父节点
            while (cur.parent.value != cur.value)
                cur = cur.parent;
            return cur;
        }

        Node insertAsLast(int p) {
            Node cur = header;
            //找到最后一个节点
            while (cur.next != null)
                cur = cur.next;
            return cur.insertAfter(p);
        }

        @Override
        public String toString() {
            if (isEmpty()) return "empty list";
            StringBuilder sb = new StringBuilder();
            Node cur = header.next;
            while (cur.next != null) {
                sb.append(cur).append("\n");
                cur = cur.next;
            }
            sb.append(cur);
            return sb.toString();
        }
    }

    static class ListWeightedQuickUnion{
        private List id = new List();

        //返回最后一个节点的值，并连接到最后一个节点
        @SuppressWarnings("UnusedReturnValue")
        int newSite(int p){
            return id.insertAsLast(p).value();
        }

        //查找节点的值
        int find(int p) {
            return id.findNode(p).value();
        }

        //查找节点
        List.Node findNode(int p){
            return id.findNode(p);
        }

        //判断节点是否连通
        boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        //连通两个节点
        void union(int p, int q) {
            List.Node pRoot = findNode(p);
            List.Node qRoot = findNode(q);
            if (pRoot == qRoot) return;
            if (pRoot.size()<qRoot.size()){
                pRoot.setParent(qRoot);
                qRoot.addSize(pRoot.size());
            }else {
                qRoot.setParent(pRoot);
                pRoot.addSize(qRoot.size());
            }
        }

        @Override
        public String toString() {
            return id.toString();
        }
    }

    public static void main(String[] args) {
        int N = 30, pairCount = 40;
        ListWeightedQuickUnion lwqu = new ListWeightedQuickUnion();
        for (int i = 0; i < N; i++)
            lwqu.newSite(i);
        ArrayGenerator.RandomPair gen = new ArrayGenerator.RandomPair(N);
        for (int i = 0; i < pairCount; i++) {
            int[] pair = gen.nextPair();
            if (lwqu.connected(pair[0], pair[1])) {
                StdOut.printf("%d %d 已连通\n", pair[0], pair[1]);
            } else {
                lwqu.union(pair[0], pair[1]);
            }
        }
        StdOut.println("\n\n连接结果如下 : \n" + lwqu);
    }
}
