import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 아기 상어 -- 덜함
 * 거리가 가까운 애들 먼저 먹어야함!!!
 */
public class BOJ_16236 {

	static int N;
	static int[][] map;
	static Point start;
	static int size = 2;
	static int eatCnt = 0;
	static int time = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					start = new Point(i, j);
				}
			}
		}

		go();

		System.out.println(time);
	}

	private static void go() {

		Point cur = start;

		System.out.println(cur);
		while (true) {
			Point next = move(cur);
			System.out.println(next);
			// 먹을 물고기가 없다..
			if (next.r == N && next.c == N) {
				return;
			}
			// 시간 업데이트
			time += Math.abs(cur.r - next.r) + Math.abs(cur.c - next.c);

			cur = next;
		}
	}

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	private static Point move(Point cur) {

		PriorityQueue<Point> q = new PriorityQueue<>();
		boolean[][] v = new boolean[N][N];
		q.offer(cur);
		v[cur.r][cur.c] = true;

		while (!q.isEmpty()) {
			cur = q.poll();
			System.out.println("---" + cur);
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc]) {
					continue;
				}
				// 자신보다 작은 물고기만 먹을 수 있다.
				if (map[nr][nc] != 0 && map[nr][nc] < size) {
					System.out.println("eating !! " + nr + " " + nc);
					// 물고기 냠냠
					map[nr][nc] = 0;
					eatCnt++;
					// 자신의 크기만큼 먹었으면 크기 1증가
					if (eatCnt == size) {
						size++;
						eatCnt = 0;
					}
					return new Point(nr, nc);
				}
				// 먹을 수 있는 물고기 찾으러 간다.
				q.offer(new Point(nr, nc));
				v[nr][nc] = true;
			}
		}

		// 더이상 먹을 물고기가 없다ㅠㅠ
		return new Point(N, N);

	}

	static class Point implements Comparable<Point>{
		int r, c, d;

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", d=" + d + "]";
		}

		@Override
		public int compareTo(Point o) {
			return o.d - this.d;
		}

	}
}
