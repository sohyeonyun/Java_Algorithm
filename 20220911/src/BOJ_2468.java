import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2468 {

	static int N, min_height, max_height;
	static int[][] map;
	static boolean[][] v;
	static int MAX = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min_height = Math.min(min_height, map[i][j]);
				max_height = Math.max(max_height, map[i][j]);
			}
		}

		v = new boolean[N][N];
		for (int i = min_height; i < max_height; i++) {
			// v 초기화
			for (int j = 0; j < N; j++) {
				Arrays.fill(v[j], false);
			}

			// bfs 탐색
			bfs(i);
		}

		System.out.println(MAX);

	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	// 안전 영역의 개수 세야함(비 높이보다 높은 지역)
	private static void bfs(int height) {
		Queue<Point> q;
		int cnt = 0; // 이 높이에서 안전영역 개수

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 물에 잠기거나 이미 방문했으면 패스
				if (map[i][j] <= height || v[i][j]) {
					continue;
				}

				q = new LinkedList<>();
				v[i][j] = true;
				q.add(new Point(i, j));

				while (!q.isEmpty()) {
					Point cur = q.poll();

					// 4방위
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];

						// 범위 벗어나거나 방문했으면 패스
						if (nx < 0 || nx >= N || ny < 0 || ny >= N || v[nx][ny]) {
							continue;
						}

						// 물에 잠기는 곳은 패스
						if (map[nx][ny] <= height) {
							continue;
						}

						// 큐에 삽입
						q.add(new Point(nx, ny));
						v[nx][ny] = true;
					}
				}
				cnt++;
			}
		}

		MAX = Math.max(MAX, cnt);

	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
