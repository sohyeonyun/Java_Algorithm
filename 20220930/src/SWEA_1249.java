import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1249 {

	static int N, res;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			res = 0;

			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			bfs();

			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.print(sb);
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1, };
	private static void bfs() {
		Queue<Data> q = new LinkedList<>();
		boolean[][] v = new boolean[N][N];
		// 시작점
		q.add(new Data(0, 0, map[0][0]));
		v[0][0] = true;

		Data cur;
		while (!q.isEmpty()) {
			cur = q.poll();
			
			if(cur.x == N-1 && cur.y == N-1) {
				res = cur.cnt;
				return;
			}

			int nx, ny;
			for (int d = 0; d < 4; d++) {
				nx = cur.x + dx[d];
				ny = cur.y + dy[d];
				// 범위 체크
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				// 방문 체크
				if(v[nx][ny]) {
					continue;
				}
				// 큐
				q.add(new Data(nx, ny, cur.cnt + map[cur.x][cur.y]));
				v[nx][ny] = true;
			}

		}
	}
	
	static class Data {
		int x, y, cnt;

		public Data(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Data [x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
		}
	}

}
