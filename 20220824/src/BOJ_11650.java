import java.util.Arrays;
import java.util.Scanner;

/*
 * 좌표 정렬하기
 */

public class BOJ_11650 {

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.x != o.x)
				return this.x - o.x;
			return this.y - o.y;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			points[i] = new Point(sc.nextInt(), sc.nextInt());
		}
		
		Arrays.sort(points);

		for(Point point : points) {
			System.out.println(point.x + " " + point.y);
		}
	}

}
