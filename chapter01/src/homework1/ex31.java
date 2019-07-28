package homework1;

import edu.princeton.cs.algs4.*;
import java.awt.Color;
import java.util.*;

public class ex31 {
	// x 轴边界
	private static double xScale = 100;
	// y 轴边界
	private static double yScale = 100;
	// 圆的半径
	private static double radius = 50;
	// 圆心
	private static Point center = new Point(50, 50);
	/*
	 * 点
	 */
	static class Point {
		private double x;
		private double y;
		public Point() { this(0,0); }
		public Point(double x, double y) { 
			this.x = x; 
			this.y = y; 
		}
		/*
		 * 获取该点偏移 angle，并相距为 distance 的点
		 * 分4个象限分别计算每个象限生成的点
		 */
		public Point newPoint(double distance, double angle) {
			double newX = 0;
			double newY = 0;
			//角度大于0 小于90度
			if (angle >= 0 && angle <= Math.PI / 2) {
				newX = x + distance * Math.cos(angle);
				newY = y + distance * Math.sin(angle);
			} else if (angle > Math.PI / 2 && angle <= Math.PI) {//角度大于90度小于180度
				newX = x - distance * Math.cos(Math.PI - angle);
				newY = y + distance * Math.sin(Math.PI - angle);
			} else if (angle > Math.PI && angle <= Math.PI * 1.5) {//角度大于180度小于270度
				newX = x - distance * Math.cos(angle - Math.PI);
				newY = y - distance * Math.sin(angle - Math.PI);
			} else {//大于270度小于360度
				newX = x + distance * Math.cos(Math.PI * 2 - angle);
				newY = y - distance * Math.sin(Math.PI * 2 - angle);
			}
			return new Point(newX, newY);
		}
	}
	
	/*
	 * 模拟在某个概率下，是否发生该事件
	 */
	public static boolean occur(double p) {
		int count = (int)(p * 100000);
		int random = StdRandom.uniform(100000);
		return random <= count;
	}
	
	/*
	 * 绘图
	 */
	public static void draw(int N, double p) {
		// 设置边界
		StdDraw.setXscale(0, xScale);
		StdDraw.setYscale(0, yScale);
		
		// 设置笔触颜色
		StdDraw.setPenColor(Color.GRAY);
		
		// 设置笔触大小
		StdDraw.setPenRadius(.001);
		
		// 生成 N 个在圆上等距离的点 N = 100
		Point[] points = new Point[N];
		//角度，360度是2π，N个点，决定在图上画几个点，角度是其中任意两个点到圆心的角度
		double angle = Math.PI * 2 / N;
		//遍历每一个点
		for(int i = 0; i < N; i++)
			//center是圆心 radius是半径，angle是每个角到起始点的圆心角度
			points[i] = center.newPoint(radius, angle * i);
		
		// 连线 100 乘 100 遍历10000次 取任意两个点进行连线
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(j != i) {
					//从数组取出两个点，不是同一个点
					Point p1 = points[i];
					Point p2 = points[j];
					//按照概率决定连不连线
					if (occur(p))
						StdDraw.line(p1.x, p1.y, p2.x, p2.y);
				}
			}
		}	
	}
	
	public static void main(String[] args) {
		draw(100, 0.1);
	}
}
