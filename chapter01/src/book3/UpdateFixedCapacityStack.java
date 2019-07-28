package book3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UpdateFixedCapacityStack<Item> {
    //数组
    private Item[] a;
    //数组的长度
    private int N;

    @SuppressWarnings("unchecked")
    public UpdateFixedCapacityStack() {
        this.a = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    @SuppressWarnings("unchecked")
    public void resize(int max) {
        //将大小为N<max的栈移动到一个新的大小为max的数组中
        Item[] temp = (Item[]) new Object[max];
        if (N >= 0) System.arraycopy(a, 0, temp, 0, N);
        a = temp;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        //将元素压入栈顶
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() {
        //从栈顶删除元素
        Item item = a[--N];
        //避免对象游离
        a[N] = null;
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }

    public static void main(String[] args) {
        UpdateFixedCapacityStack<String> s = new UpdateFixedCapacityStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}

