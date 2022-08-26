import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {

	static int N, M;
	static int[][] map;
	static boolean[][][] v; // 좌표 위치 방문체크, 벽부쉈는지
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int result = -1;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		v = new boolean[N][M][2];

		bfs();

		System.out.println(result);

	}

	private static void bfs() {
		// 현재 위치, 이동칸, 벽 부쉈는지
		Queue<Data> q = new LinkedList<>();
		q.offer(new Data(0, 0, 1, 0));
		v[0][0][0] = true;

		while (!q.isEmpty()) {
			Data d = q.poll();
			// 최종 위치에 도달
			if (d.x == N - 1 && d.y == M - 1) {
				result = d.cnt;
				return;
			}
			// 사방위
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				// 맵 벗어나거나 방문했으면 패스
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || v[nx][ny][d.flag]) {
					continue;
				}
				// 다음 칸이 벽으로 막혔으면
				if (map[nx][ny] == 1) {
					if (d.flag == 1) { // 벽으로 막혔는데 이미 벽 뚫기 썼으면
						continue;
					} else { // 벽 부수고 이동
						q.offer(new Data(nx, ny, d.cnt + 1, 1));
						v[nx][ny][1] = true;
					}
				} else { // 다음 칸으로 한 칸 이동
					q.offer(new Data(nx, ny, d.cnt + 1, d.flag));
					v[nx][ny][d.flag] = true;
				}

			}
		}
		
	}

	static class Data {
		int x, y;
		int cnt;
		int flag; // 벽 부숨(1), 안부숨(0)

		public Data(int x, int y, int cnt, int flag) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.flag = flag;
		}
	}

}
