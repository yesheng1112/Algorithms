package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//找到一个三元组中间的数最小
public class ex18 {
	/*
	 * 思路 :
	 * 
	 * 二分查找，若 mid 处于一个单调递增三元组，就让 hi = mid - 1
	 * 如果 mid 处于一个单调递减三元组，就让 lo = mid + 1
	 * 如果 mid 是一个局部最大值，就随便选一边
	 * 否则就返回 mid
	 * 
	 */
	public static int localMinimum(int[] a) {
	    if (a == null || a.length < 2)
	        throw new IllegalArgumentException();
	    if (a[0] < a[1])
	        return a[0];
	    if (a[a.length - 2] > a[a.length - 1])
	        return a[a.length - 1];
		int lo = 1, hi = a.length - 2, mid;
		while (lo <= hi) {
			mid = (lo + hi) >> 1;
			//满足条件直接返回结果
			if       (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) return a[mid];
			//递增，后面满足，前面不满足，移动后面的标志
			else if  (a[mid - 1] < a[mid] && a[mid] < a[mid + 1]) hi = mid - 1;
			//递减，前面满足，后面不满足，移动前面的标志
			else if  (a[mid - 1] > a[mid] && a[mid] > a[mid + 1]) lo = mid + 1;
			//都不满足，移动前面的位置
			else     lo = mid + 1;
			StdOut.printf("mid = %d lo = %d hi = %d\n", mid, lo, hi);
		}
		return -1;
	}
	/*
	 * 产生元素不重复的随机数组
	 */
	public static int[] sourceArr(int N) {
		//产生不重复的集合
		Set<Integer> set = new HashSet<>();
		//产生随机数，添加到set集合中
		while (set.size() < N) 
		    set.add(StdRandom.uniform(0, 100));
		int[] rel = Arrays
		        .stream(set.toArray(new Integer[0]))
		        .mapToInt(Integer::valueOf)
		        .toArray();
		for (int i = 0; i < rel.length; i++) {
		    int r = i + StdRandom.uniform(rel.length - i);
		    int tmp = rel[r];
		    rel[r] = rel[i];
		    rel[i] = tmp;
		}
		//保证所有的情况在最坏的情况下
		if (rel[0] < rel[1]) {
		    int tmp = rel[1];
		    rel[1] = rel[0];
		    rel[0] = tmp;
		}
		if (rel[rel.length - 2] > rel[rel.length - 1]) {
		    int tmp = rel[rel.length - 2];
		    rel[rel.length - 2] = rel[rel.length - 1];
		    rel[rel.length - 1] = tmp;
		}
		return rel;
	}
	/*
	 * 打印数组
	 */
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			if ((i + 1) % 10 == 0)
				StdOut.printf("%4d\n", arr[i]);
			else
				StdOut.printf("%4d", arr[i]);
		StdOut.println();
	}
	public static void main(String[] args) throws Exception {
		int[] arr = sourceArr(10);
		printArray(arr);
		StdOut.println("局部最小值 : " + localMinimum(arr));
	}
	// output
	/*
	 *  62  4 43 65 14 11 17 55 54 62
		局部最小值 : 6
		
		54 27 19 53  2 82 66 51 37 86
		局部最小值 : -1
		79 79 83 39 43 45  7 87 21 72
		局部最小值 : 4
	 */
}