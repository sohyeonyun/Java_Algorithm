import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1261 {

	static int N, M;
	static int[][] map, v;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		bfs();

		System.out.println(v[N - 1][M - 1]);

	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	private static void bfs() {

		v = new int[N][M];
		for(int i=0; i<N; i++) {
			Arrays.fill(v[i], 999_999_999);
		}
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		v[0][0] = 0;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			// 도착점이면 패수
			if (cur.x == N - 1 && cur.y == M - 1) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				// 이미 더 작은 벽부수기로 방문했으면 패스
				if (v[nx][ny] <= v[cur.x][cur.y] + map[nx][ny]) {
					continue;
				}
				v[nx][ny] = v[cur.x][cur.y] + map[nx][ny];
				q.offer(new Point(nx, ny));
			}
		}
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
