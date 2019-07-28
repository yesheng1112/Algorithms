package book3;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    @SuppressWarnings("unchecked")
    //栈元素
    private Item[] a = (Item[]) new Object[1];

    //元素数量
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    @SuppressWarnings("unchecked")
    private void resize(int max) {
        //将栈移动到一个大小为max的新数组
        Item[] temp = (Item[]) new Object[max];
        if (N >= 0) System.arraycopy(a, 0, temp, 0, N);
        a = temp;
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

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        //支持后进先出的迭代
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}
