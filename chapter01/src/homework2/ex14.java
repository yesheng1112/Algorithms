package homework2;

import edu.princeton.cs.algs4.StdOut;

import java.util.regex.Pattern;

public class ex14 {
    static class Transaction {
        private final String who;
        private final String when;
        private final String amount;

        Transaction(String transaction) {
            String[] result = transaction.split("\\s+");
            if (result.length != 3)
                throw new RuntimeException("input" + transaction + "invalid");

            who = result[0];
            if (Pattern.compile("[^a-zA-Z'·]").matcher(who).find())
                throw new RuntimeException("invalid name " + who);

            when = result[1];
            if (!Pattern.compile("\\d{1,4}[-\\\\]\\d{1,2}[-\\\\]\\d{1,2}").matcher(when).find())
                throw new RuntimeException("invalid date " + when);

            amount = result[2];
            if (!Pattern.compile("[1-9]\\d*(\\.\\d+)?").matcher(amount).find())
                throw new RuntimeException("invalid amount " + amount);

        }

        String who() {
            return who;
        }

        String when() {
            return when;
        }

        String amount() {
            return amount;
        }

        public String toString() {
            return String.format("Name : %s\nDate : %s\nAmount : %s", who, when, amount);
        }

        public boolean equals(Object other) {
            if (other == this) return true;
            if (other == null) return false;
            if (other.getClass() != this.getClass()) return false;
            Transaction that = (Transaction) other;
            return (this.who.equals(that.who)) && (this.when.equals(that.when)) && (this.amount.equals(that.amount));
        }
    }

    public static void main(String[] args) {
        Transaction
                t1 = new Transaction("YeSheng   	1996-1-2 	 65790.24945351756"),
                t2 = new Transaction("YeSheng 	1996-1-2 	 65790.24945351756");
        StdOut.println(t1.equals(t2));

    }
}
