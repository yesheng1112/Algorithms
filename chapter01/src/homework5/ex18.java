package homework5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class ex18 {
    static RandomBag<Connection> randomGridGenerator(int N) {
        RandomBag<Connection> rb = new RandomBag<>();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N - 1; j++)
                //p(x,y)q(x,y+1)
                //p(x,y+1)q(x,y)
                if (StdRandom.bernoulli(0.5))
                    rb.add(new Connection(i * N + j, i * N + j + 1));
                else
                    rb.add(new Connection(i * N + j + 1, i * N + j));

        for (int i = 0; i < N - 1; i++)
            for (int j = 0; j < N; j++)
                //p(x,y) q(x+1,y)
                //p(x+1,y) q(x,y)
                if (StdRandom.bernoulli(0.5))
                    rb.add(new Connection(i * N + j, i * N + N + j));
                else
                    rb.add(new Connection(i * N + N + j, i * N + j));
        return rb;
    }

    static class Connection {
        int p;
        int q;

        Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }

        public String toString() {
            return String.format("{%d,%d}", p, q);
        }
    }

    static class RandomBag<T> implements Iterable<T> {

        @SuppressWarnings("unchecked")
        private T[] items = (T[]) new Object[1];
        private int size;

        RandomBag() {
        }

        boolean isEmpty() {
            return size == 0;
        }

        int size() {
            return size;
        }

        void resize(int newSize) {
            @SuppressWarnings("unchecked")
            T[] newItems = (T[]) new Object[newSize];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }

        void add(T item) {
            if (size == items.length)
                resize(2 * size);
            items[size++] = item;
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int index;
                @SuppressWarnings("unchecked")
                private T[] random = (T[]) new Object[size];

                {
                    System.arraycopy(items, 0, random, 0, size);
                    for (int i = 0; i < size; i++) {
                        int r = i + StdRandom.uniform(size - i);
                        T tmp = random[r];
                        random[r] = random[i];
                        random[i] = tmp;
                    }
                }

                @Override
                public boolean hasNext() {
                    return index < size;
                }

                @Override
                public T next() {
                    return random[index++];
                }
            };
        }
    }

    public static void main(String[] args) {
        for (Connection connection : randomGridGenerator(5)) {
            StdOut.println(connection);
        }
    }
}
