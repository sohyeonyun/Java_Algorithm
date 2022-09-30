import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 탈주범 검거 - 다시풀기~~~~~~~~~
 */
public class SWEA_1953 {

	static int res;
	static int N, M, R, C, L;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			res = 0;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs();

			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.print(sb);
	}

	static int[][] dx = { {}, { -1, 1, 0, 0 }, { -1, 1 }, { 0, 0 }, { -1, 0 }, { 1, 0 }, { 1, 0 }, { -1, 0 } };
	static int[][] dy = { {}, { 0, 0, -1, 1 }, { 0, 0 }, { -1, 1 }, { 0, 1 }, { 0, 1 }, { 0, -1 }, { 0, -1 } };

	private static void bfs() {
		Queue<Data> q = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		// 시작점 - 맨홀 위치
		q.add(new Data(R, C, 1));
		v[R][C] = true;

		Data cur;
		while (!q.isEmpty()) {
			cur = q.poll();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(v[i][j] == true ? 1 +" ": 0 + " ");
				}
				System.out.println();
			}
			
			res++;
			System.out.println(cur + " " + res);

			int nx, ny;
			int type = map[cur.x][cur.y];
			for (int d = 0; d < dx[type].length; d++) {
				nx = cur.x + dx[type][d];
				ny = cur.y + dy[type][d];
				// 범위 체크
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				// 방문 체크, 이동 가능 여부
				if(v[nx][ny] || map[nx][ny] == 0 || cur.cnt >= L) {
					continue;
				}
				// 큐
				q.add(new Data(nx, ny, cur.cnt + 1));
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
