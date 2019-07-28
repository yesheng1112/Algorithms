package homework3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ex39 {
    static class RingBuffer<T> {
        private int size, capacity, head, tail;
        private T[] items;
        //锁对象
        private ReentrantLock lock;
        //条件对象，缓冲区为空
        private Condition empty;
        //条件对象，缓冲区满了
        private Condition full;

        /**
         * 初始化环形缓冲区
         *
         * @param capacity 缓冲区容量
         */
        @SuppressWarnings("unchecked")
        RingBuffer(int capacity) {
            this.capacity = capacity;
            items = (T[]) new Object[capacity];
            lock = new ReentrantLock();
            empty = lock.newCondition();
            full = lock.newCondition();
        }

        //出缓冲区
        T dequeue() throws InterruptedException {
            /*
             * 当获取锁时，锁资源可用，那么当前线程成功获得锁，同时持有count设置为1，返回true.
             * 当获取锁时，锁资源可用，当前线程已持有该锁，它成功获取该锁，同时持有count增加1，返回true.
             * 当获取锁时，锁资源不可用，那么该线程开始阻塞休眠等待，但是等待过程中如果有中断事件，那么会停止等待，立即返回.
             * 当获取锁时，锁资源不可用，线程开始阻塞休眠等待，如果等待过程中锁资源变为可用，那么当前线程成功获得锁，同时持有count设置为1，返回true.
             * */
            lock.lockInterruptibly();
            try {
                // 如果当前队列为空，那么让当前线程在 empty 条件上陷入等待
                while (isEmpty()) {
                    StdOut.println("♣️Consumer is waiting ...");
                    empty.await();
                }

                // 如果当前线程被唤醒，那么说明由元素可供出列
                T deq = items[head];
                //head在[0-capacity)之间，从0开始，每次加1，加到capacity的时候，又从0开始
                head = (head + 1) % items.length;
                size--;

                // 出列了一个元素，那么如果由线程在 full 条件上陷入等待，我们将其唤醒
                full.signalAll();
                return deq;
            } finally {
                lock.unlock();
            }
        }

        //入缓冲区
        void enqueue(T item) throws InterruptedException {
            //加入的元素为空的直接抛出异常
            if (item == null) throw new NullPointerException();
            //加锁
            lock.lockInterruptibly();
            try {

                // 如果入队时发现队列已经满了，那么让当前线程在 full 条件上陷入等待
                while (isFull()) {
                    StdOut.println("♥️Producer is waiting ...");
                    full.await();
                }

                // 如果当前线程被唤醒，说明队列中出现可供入队的空位，我们执行入队操作
                size++;
                items[tail] = item;
                //tail在[0-capacity)之间，从0开始，每次加1，加到capacity的时候，又从0开始
                tail = (tail + 1) % items.length;

                // 入队一次后，如果由在 empty 条件上陷入等待的线程，我们将其唤醒
                empty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == capacity;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            for (int i = 0; i < items.length; i++)
                sb.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
            return sb.toString();
        }
    }

    static class Food {
        private static int counter = 0;
        private final int id = counter++;

        public String toString() {
            return "food " + id;
        }
    }

    //消费者线程
    static class Consumer implements Runnable {
        private RingBuffer<Food> buffer;

        public Consumer(RingBuffer<Food> buffer) {
            this.buffer = buffer;
        }

        public void run() {
            try {
                //如果线程不被中断
                while (!Thread.interrupted()) {
                    Food food = buffer.dequeue();
                    StdOut.println("️Consumer eats food " + food);
                    TimeUnit.MILLISECONDS.sleep(StdRandom.uniform(300));
                }
            } catch (InterruptedException e) {
                StdOut.println("Consumer was interrupted while eating food ");
            }
        }
    }

    //生产者线程
    static class Producer implements Runnable {
        private RingBuffer<Food> buffer;

        public Producer(RingBuffer<Food> buffer) {
            this.buffer = buffer;
        }

        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Food food = new Food();
                    TimeUnit.MILLISECONDS.sleep(StdRandom.uniform(300));
                    buffer.enqueue(food);
                    StdOut.println("Producer produced food " + food);
                }
            } catch (InterruptedException e) {
                StdOut.println("Producer was interrupted while making delicious food");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        RingBuffer<Food> buffer = new RingBuffer<>(2);
        //线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Consumer(buffer));
        exec.execute(new Producer(buffer));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
