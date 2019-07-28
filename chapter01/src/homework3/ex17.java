package homework3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.regex.Pattern;

public class ex17 {
    /*
     * 思路 :
     *
     * 没啥好说的
     */
    static class Transaction {
        private final String who;
        private final String when;
        private final String amount;

        Transaction(String deal) {
            String[] strs = deal.split("\\s+");
            if (strs.length < 3)
                throw new RuntimeException("less arguments than reqiured");
            if (Pattern.compile("[^a-zA-Z']").matcher(strs[0]).find())
                throw new RuntimeException("name string format error");
            if (!Pattern.compile("\\d{1,4}[-/]\\d{1,2}[-/]\\d{1,2}").matcher(strs[1]).find())
                throw new RuntimeException("date format error");
            if (!Pattern.compile("[1-9]\\d*(\\.\\d+)?").matcher(strs[2]).find())
                throw new RuntimeException("number format error");
            who = strs[0];
            when = strs[1];
            amount = strs[2];
        }

        public String toString() {
            return String.format("【姓名 : %5s, 日期 : %5s, 金额 : %5s】", who, when, amount);
        }
    }

    /*
     * 读取交易订单
     */
    public static Transaction[] readTransactions(String name) {
        In in = new In(name);
        Queue<Transaction> queue = new Queue<>();
        while (!in.isEmpty())
            queue.enqueue(new Transaction(in.readLine()));
        int N = queue.size();
        Transaction[] arr = new Transaction[N];
        for (int i = 0; i < N; i++)
            arr[i] = queue.dequeue();
        return arr;
    }

    public static void main(String[] args) {
        for (Transaction t : readTransactions("C:\\Users\\YS\\Desktop\\algs4-data\\algs4-data\\transactionTest.txt"))
            StdOut.println(t);
    }
}