import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 방향 전환
 */
public class SWEA_8382_sol {

	static int x1, y1, x2, y2;
	static int res;
	static boolean[][][] v; // 좌표 방문체크 + 가로세로 방향

	static int[] dx = { -1, 1, 0, 0 }; // 가로 세로
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			res = -1;

			v = new boolean[201][201][2];

			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken()) + 100;
			y1 = Integer.parseInt(st.nextToken()) + 100;
			x2 = Integer.parseInt(st.nextToken()) + 100;
			y2 = Integer.parseInt(st.nextToken()) + 100;

			bfs();
			
			sb.append("#").append(t).append(" ").append(res).append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs() {
		// 큐 만들어서 처음 것 집어넣고 방문 체크
		Queue<Data> q = new LinkedList<>();
		q.offer(new Data(x1, y1, 0, 0)); // 가로 이동
		q.offer(new Data(x1, y1, 0, 1)); // 세로 이동
		v[y1][x1][0] = true;
		v[y1][x1][1] = true;

		// 큐가 빌 때까지
		Data cur;
		while (!q.isEmpty()) {
			cur = q.poll();

			if (cur.x == x2 && cur.y == y2) {
				res = cur.cnt;
				return;
			}

			// 현재 진행되는 방향에 따른 4방위 탐색
			int nx, ny;
			switch (cur.dir) {
			case 0: // 가로 이동
				for (int d = 2; d < 4; d++) {
					nx = cur.x + dx[d];
					ny = cur.y + dy[d];
					// 배열 범위 넘어갔나
					if (nx < 0 || nx > 200 || ny < 0 || ny > 200) {
						continue;
					}
					// 이미 방문했나 (가로로 들어왔기 때문에 다음은 세로1 로 들어가야함)
					if (v[ny][nx][1]) {
						continue;
					}
					// 로직 구현했나
					// 큐에 삽입, 방문체크
					q.offer(new Data(nx, ny, cur.cnt + 1, 1));
					v[ny][nx][1] = true;
				}
				break;
			case 1: // 세로 이동
				for (int d = 0; d < 2; d++) {
					nx = cur.x + dx[d];
					ny = cur.y + dy[d];
					// 배열 범위 넘어갔나
					if (nx < 0 || nx > 200 || ny < 0 || ny > 200) {
						continue;
					}
					// 이미 방문했나 (세로로 들어왔기 때문에 다음은 가로0 로 들어가야함)
					if (v[ny][nx][0]) {
						continue;
					}
					// 로직 구현했나
					// 큐에 삽입, 방문체크
					q.offer(new Data(nx, ny, cur.cnt + 1, 0));
					v[ny][nx][0] = true;
				}
				break;
			}

		}

	}

	// 현재 위치, 이동위치, 온방향
	static class Data {
		int x, y;
		int cnt;
		int dir; // 가로(0) 세로(1)

		public Data(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
	}

}
