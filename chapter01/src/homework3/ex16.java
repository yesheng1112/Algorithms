package homework3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.regex.Pattern;

public class ex16 {
	/*
	 * 思路 :
	 *
	 * 没啥好说的
	 */
	static class Date {
		private final int day;
		private final int month;
		private final int year;

		Date(String dateString) {
			String[] strs = dateString.split("/");
			if (strs.length < 3)
				throw new RuntimeException("incorrect date format");
			if (!Pattern.compile("\\d{1,2}").matcher(strs[0]).find() ||
					!Pattern.compile("\\d{1,2}").matcher(strs[1]).find() ||
					!Pattern.compile("\\d{1,4}").matcher(strs[2]).find())
				throw new RuntimeException("invalid month or day or year" + dateString);
			month = Integer.parseInt(strs[0]);
			day = Integer.parseInt(strs[1]);
			year = Integer.parseInt(strs[2]);
		}

		public String toString() {
			return String.format("%d年%d月%d日", year, month, day);
		}
	}

	/*
	 * 读取 Date
	 */
	public static Date[] readDates(String name) {
		In in = new In(name);
		Queue<Date> queue = new Queue<>();
		while (!in.isEmpty())
			queue.enqueue(new Date(in.readString()));
		int N = queue.size();
		Date[] a = new Date[N];
		for (int i = 0; i < N; i++)
			a[i] = queue.dequeue();
		return a;
	}

	public static void main(String[] args) {
		for (Date date : readDates("C:\\Users\\YS\\Desktop\\algs4-data\\algs4-data\\dateTest.txt"))
			StdOut.println(date);
	}
}