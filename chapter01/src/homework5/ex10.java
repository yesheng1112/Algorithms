package homework5;

public class ex10 {
    public static void main(String[] args) {
        /*
         *
         * void union(int p, int q) {
         *
         * int pRoot = find(p);
         * int qRoot = find(q);
         *
         * if (pRoot == qRoot) return;
         *
         * if (size(pRoot) < size(qRoot) {
         *
         * 		// 把 pRoot为根结点的子树连接到 qRoot为根结点子树，说明 qRoot是包含结点多的子树
         * 		// 我们通过将小子树移接到大子树上，完成查找路径的压缩
         * 		// 从当前结点向上追溯至根结点，并将其作为需要被附着子树的结点或者作为待移接的结点，也是
         *      // 达到路径压缩这个目的的手段之一
         *      // 假设 A 子树在结点 u 处达到最大高度 h1，B 子树在结点 m 处达到最大高度 h2
         *      // 根据加权 quick-union 算法进行连接，形成的新树高度将为 Min{h1, h2}
         *      // 若将 id[find(p)] 直接连接到 q 上，会造成新树的高度为两子树高度之和，没有找到根节点，直接连上，所以就是两个根节点的和
         *      // 那么路径压缩的效果将失效
         *      // 但是我们的 find 算法将追溯至两者的根结点，因此 connected 的正确性可以保证
         *
         * 		id[pRoot] = qRoot;
         * 		size[qRoot] += size[pRoot];
         * } else {
         * 		id[qRoot] = pRoot;
         * 	    size[pRoot] += size[qRoot];
         * }
         *
         * }
         *
         */
    }
}
