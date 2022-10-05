import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 치즈
 */
public class BOJ_2636 {

	static int N, M;
	static int[][] map;
	static int cheese = 0;
	static boolean[][] v;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 치즈 개수 체크
				if (map[i][j] == 1) {
					cheese++;
				}
			}
		}

		simulate();
	}

	private static void simulate() {

		int cnt = 0;
		int time = 0;

		while (true) {
			// 모든 치즈 다 녹으면 종료
			if (cheese == 0) {
				break;
			}
			// 한시간 지남.
			time++;

			v = new boolean[N][M];
			// 치즈 바깥 부분 표시
			bfs(0, 0);

			// 테두리 치즈 녹이기 -> 녹은 치즈 개수
			cnt = melting();
			// 개수 세기
			cheese -= cnt;
		}

		System.out.println(time);
		System.out.println(cnt);

	}

	private static int melting() {
		// 녹인 치즈 개수
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 치즈라면
				if (map[i][j] == 1) {
					// 테두리인지( 상하좌우 중 방문체크 돼있는지)
					if (v[i - 1][j] || v[i + 1][j] || v[i][j - 1] || v[i][j + 1]) {
						// 치즈 없앰..
						map[i][j] = 0;
						cnt++;
					}
				}
			}
		}

		return cnt;
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	private static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(r, c));
		v[r][c] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				// 방문하지 않았고 빈칸이면 방문처리
				if (!v[nr][nc] && map[nr][nc] == 0) {
					v[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}
		}
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
