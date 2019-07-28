package homework3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;

public class ex34 {

    static class RandomBag<Item> implements Iterable<Item> {

        private int N;

        @SuppressWarnings("unchecked")
        private Item[] a = (Item[]) new Object[1];

        public RandomBag() {
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        @SuppressWarnings("unchecked")
        public void resize(int max) {
            //将栈移动到一个大小为max的新数组
            Item[] temp = (Item[]) new Object[max];
            if (N >= 0) System.arraycopy(a, 0, temp, 0, N);
            a = temp;
        }

        public void add(Item item) {
            if (N == a.length) resize(a.length * 2);
            a[N++] = item;
        }

        @SuppressWarnings("unchecked")
        private Item[] randomCopy() {
            Item[] random = (Item[]) new Object[N];
            for (int i = 0; i < N; i++)
                random[i] = a[i];
            /*
             * r = [i, N)
             */
            //从第一个元素开始，生成随机下标进行交换，交换完的下标排除在外，就保证每一个下标都能遍历到
            for (int i = 0; i < random.length; i++) {
                int r = i + StdRandom.uniform(random.length - i);
                Item tmp = random[r];
                random[r] = random[i];
                random[i] = tmp;
            }
            return random;
        }

        @Override
        public Iterator<Item> iterator() {
            return new RandomBagIterator();
        }

        private class RandomBagIterator implements Iterator<Item> {

            private Item[] random = randomCopy();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < random.length;
            }

            @Override
            public Item next() {
                return random[index++];
            }
        }

    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        for (int i = 0; i < 10; i++) {
            a[i] = StdRandom.uniform(0, 10);
        }
        StdOut.println(Arrays.toString(a));

        RandomBag<Integer> randomBag = new RandomBag<>();
        for (int i = 0; i < a.length; i++) {
            randomBag.add(a[i]);
        }

        for (Integer integer : randomBag) {
            StdOut.print(integer + " ");
        }
    }

}
